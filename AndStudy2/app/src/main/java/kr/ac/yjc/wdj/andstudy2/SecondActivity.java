package kr.ac.yjc.wdj.andstudy2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by siri on 2018-01-10.
 */

public class SecondActivity extends Activity {
    TextView textView;
    Button yes, no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        textView = (TextView)findViewById(R.id.textView2);
        textView.setText("1번이 가장 멍청한 것 같다.");

        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.mainactivity);
            }
        });
    }
}

