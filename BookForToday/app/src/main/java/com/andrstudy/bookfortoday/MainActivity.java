package com.andrstudy.bookfortoday;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ConstraintLayout layout[] = new ConstraintLayout[5];
    Button button[] = new Button[5];

    //생각하기를 ㅍ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout[0] = (ConstraintLayout)findViewById(R.id.layout1);
        layout[1] = (ConstraintLayout)findViewById(R.id.layout2);
        layout[2] = (ConstraintLayout)findViewById(R.id.layout3);
        layout[3] = (ConstraintLayout)findViewById(R.id.layout4);
        layout[4] = (ConstraintLayout)findViewById(R.id.layout5);

        button[0] = (Button)findViewById(R.id.button1);
        button[1] = (Button)findViewById(R.id.button2);
        button[2] = (Button)findViewById(R.id.button3);
        button[3] = (Button)findViewById(R.id.button4);
        button[4] = (Button)findViewById(R.id.button5);

        for(int i = 0; i < button.length; i++){
            button[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < button.length; i++){
            if(v.getId() == button[i].getId()){
                layout[i].setVisibility(View.VISIBLE);
            }else{
                layout[i].setVisibility(View.INVISIBLE);
            }
        }
    }
}
