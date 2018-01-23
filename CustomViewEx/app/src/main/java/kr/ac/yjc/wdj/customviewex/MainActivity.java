package kr.ac.yjc.wdj.customviewex;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ListView list = new ListView(this);
        Drawable icon = getResources().getDrawable(R.drawable.dnabo_rain);
        MyData m1 = new MyData(icon,new String[]{"A","B","C"});
        MyData m2 = new MyData(icon,new String[]{"D","E","F"});
        MyData m3 = new MyData(icon,new String[]{"H","I","J"});

        ArrayList<MyData> total = new ArrayList<MyData>();  // 공급되는 데이터
        total.add(m1);
        total.add(m2);
        total.add(m3);

        MyAdapter myadapter = new MyAdapter(this,total);

        list.setAdapter(myadapter);
        //어뎁터
        setContentView(list);
    }
}