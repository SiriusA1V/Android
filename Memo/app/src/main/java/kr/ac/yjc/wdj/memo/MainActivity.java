package kr.ac.yjc.wdj.memo;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    SQLiteDatabase sd;
    Cursor cursor;
    ListView listview;
    Button button;
    String _id;
    SimpleCursorAdapter adapter;
    LinearLayout layout;


//    class MyListener implements Dialog.OnClickListener{
//
//        @Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            if (i == Dialog.BUTTON_POSITIVE){
//
//            }else if(i == Dialog.BUTTON_NEGATIVE){
//                Toast.makeText(MainActivity.this, "취소하셨습니다", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    MyListener myListener = new MyListener();


    Dialog.OnClickListener myListener = new Dialog.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == Dialog.BUTTON_POSITIVE){

                String query = "delete from note where _id = "+_id+";";
                sd.execSQL(query);

                //애니메이션에서 에러
                Animation a = new TranslateAnimation(0, 500, 0, 0);
                a.setDuration(1000);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        cursor = sd.query("note", null, null, null, null, null, null);
                        adapter.changeCursor(cursor);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}

                });
                layout.startAnimation(a);


            }else if(i == Dialog.BUTTON_NEGATIVE){
                Toast.makeText(MainActivity.this, "취소하셨습니다", Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        sd = openOrCreateDatabase("memo.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        String schema =
                "create table note(_id integer primary key autoincrement, " +
                        "title text not null , body text not null);";
        try {
            sd.execSQL(schema);
        }catch (Exception ignore) {
            System.out.print("오류");
        }

        ContentValues values = new ContentValues();
        values.put("title","first");
        values.put("body","hello");
        sd.insert("note",null,values);

        listview = getListView();

    }

    @Override
    protected void onStop() {
        super.onStop();
        cursor.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cursor = sd.query("note",null,null,null,null,null,null);
        adapter = new SimpleCursorAdapter(this,
                R.layout.line,
                cursor,
                new String[]{"_id","title"}, //배열값 세팅과 동시에 객체화
                new int[]{R.id.textView5,R.id.textView4}
               /* 한줄의 모양,
                공급되는 데이터,
                어떤데이터를,
                어디에*/
        );
        listview.setAdapter(adapter);

        //롱클릭 리스너
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout layout = (LinearLayout)view;
                TextView t = (TextView)layout.getChildAt(0);
                _id = t.getText().toString();

                new AlertDialog.Builder(MainActivity.this).setTitle("Title").setMessage("지울래?")
                        .setPositiveButton("YES", myListener).setNegativeButton("NO", myListener).show();

                /*Toast.makeText(MainActivity.this,"longclick",Toast.LENGTH_SHORT).show();*/
                return true; // true 하면 다른 클릭이 안먹힘
            }
        });
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        layout = (LinearLayout)v;
        TextView t = (TextView) layout.getChildAt(0);
        String _id = t.getText().toString();
        Toast.makeText(this,"_id="+_id,Toast.LENGTH_SHORT).show();
        // v 클릭된 한줄을 통째로 넘겨준다 리니어레이아웃이 들어있다고 생각 View v = new LinearLayout()

        String title = "";
        String body = "";

        cursor.moveToFirst();   //cursor 데이터 맨 위에는 필드명이 있고, 첫번째째 줄부터

        while( cursor.isAfterLast() == false){
            if( _id.equals(cursor.getString(0))){   // 0번쨰 필드 = "_id" 읽어오기
                title += cursor.getString(1);    // 1번째 필드 = "title" 읽어오기
                body += cursor.getString(2);    // 2번쨰 필드 = "body" 읽어오기
                break;
            }

            cursor.moveToNext();
        }

        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("action", "read");
        i.putExtra("title", title);
        i.putExtra("body", body);
        startActivity(i);
    }
}