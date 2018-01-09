package com.andrstudy.colorpreview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener{
    private EditText editText;
    private Button button, button2;
    private LinearLayout layoutTop, layoutBottom;

    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String text1 = "#"+editText.getText().toString();
            boolean text2= (Pattern.matches("^[a-fA-F0-9]{6}$", editText.getText().toString()))? true : false;

            if(v.getId() == R.id.button && text2){
                layoutTop.setBackgroundColor(Color.parseColor(text1));
            }else if(v.getId() == R.id.button2 && text2){
                layoutBottom.setBackgroundColor(Color.parseColor(text1));
            }else{
                editText.setText("입력 제대로");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (Button)findViewById(R.id.button2);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        layoutTop = (LinearLayout) findViewById(R.id.layoutTop);
        layoutBottom = (LinearLayout)findViewById(R.id.layoutBottom);

        button.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);
    }
}
