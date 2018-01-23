package kr.ac.yjc.wdj.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by siri on 2018-01-15.
 */

public class SecondActivity extends Activity{
    EditText e_title, e_body;
    Button button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        e_title = (EditText)findViewById(R.id.title1);
        e_body = (EditText)findViewById(R.id.body);
        button2 = (Button)findViewById(R.id.button2);

        Intent i = getIntent();

        switch( i.getStringExtra("action") ){
            case "read":
                e_title.setText(i.getStringExtra("title"));
                e_body.setText(i.getStringExtra("body"));
                e_title.setEnabled(false);  //수정이 불가능 해짐
                button2.setText("BACK");
                break;
            case "write":
                button2.setText("SAVE");
                break;
        }
    }
}
