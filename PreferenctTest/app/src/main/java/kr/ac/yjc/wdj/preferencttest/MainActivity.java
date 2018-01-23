package kr.ac.yjc.wdj.preferencttest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("test", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("age", "30");
    }
}
