package kr.ac.yjc.wdj.cyh;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button, button2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        button2 = (Button)findViewById(R.id.button5);
        button2.setOnClickListener(this);
    }

    public void makedb(){
        db = openOrCreateDatabase("list.db", MODE_WORLD_WRITEABLE, null);
        db.execSQL("create table list (data text, web text, what text, tool text, dbms text);)");
        db.execSQL("insert into list(data, web, what, tool, dbms) values ('20xx-2017', '회원관리', 'DB 및 DB API', 'PHP', 'Oracle');");
        db.execSQL("insert into list(data, web, what, tool, dbms) values ('20xx-20xx', '쇼핑몰', 'DB 및 DB API', 'PHP', 'Oracle');");
        db.execSQL("insert into list(data, web, what, tool, dbms) values ('20xx-20xx', '쇼핑몰', 'DB 및 DB API', 'PHP', 'Oracle');");
    }

    public void sendSMS(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-4093-8031"));
        intent.putExtra("sms_body", "이력서 앱을 통한 연락입니다");
        startActivity(intent);
    }

    public void sendMail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"siriusa1v@naver.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "연락을 부탁합니다");
        intent.putExtra(Intent.EXTRA_TEXT, "이력서 앱을 통한 연락입니다.");
        startActivity(Intent.createChooser(intent, "Select Mail Application"));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button.getId()){
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(intent);
        }else if(v.getId() == button2.getId()){
            Intent intent = new Intent(getApplicationContext(), ProjectActivity.class);
            startActivity(intent);
        }
    }
}
