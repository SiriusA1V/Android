package kr.ac.yjc.wdj.andstudy2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button:
                //명시적 화면 전환
                Intent i = new Intent(this, SecondActivity.class);
                startActivity(i);
                break;

            case R.id.button2:
                //암시적 화면 전환
                Uri number = Uri.parse("tel:0211230");
                Intent dial = new Intent(Intent.ACTION_DIAL, number);
                startActivity(dial);
                break;

            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}
