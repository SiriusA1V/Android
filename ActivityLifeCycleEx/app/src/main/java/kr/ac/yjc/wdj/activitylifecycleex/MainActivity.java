package kr.ac.yjc.wdj.activitylifecycleex;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("##########", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("##########", "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("##########", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("##########", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("##########", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("##########", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("##########", "onDestroy");
    }
}
