package com.andrstudy.guiend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private EditText editText, editText2;
    private RadioButton radioButton, radioButton2;
    private CheckBox checkBox, checkBox2, checkBox3;
    private ProgressBar progressBar;
    private Button button, button2;
    private int test, test2=0, test3=0, count=0;

    private void count(){
        if(count == 4){
            button.setEnabled(true);
        }else if(count < 4){
            button.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        radioButton = (RadioButton)findViewById(R.id.radioButton);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        editText.addTextChangedListener(text1);
        editText2.addTextChangedListener(text2);

        radioButton.setOnClickListener(this);
        radioButton2.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(this);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    private TextWatcher text1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(editText.length() > 0 && test2 == 0){
                progressBar.incrementProgressBy(1);
                test2++;
                count++;
                count();
            }else if(editText.length() == 0 && test2 == 1){
                progressBar.incrementProgressBy(-1);
                test2--;
                count--;
                count();
            }
        }
    };

    private TextWatcher text2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(editText2.length() > 0 && test3 == 0){
                progressBar.incrementProgressBy(1);
                test3++;
                count++;
                count();
            }else if(editText2.length() == 0 && test3 == 1){
                progressBar.incrementProgressBy(-1);
                test3--;
                count--;
                count();
            }
        }
    };

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            progressBar.incrementProgressBy(1);
            count++;
            count();
        }else{
            progressBar.incrementProgressBy(-1);
            count--;
            count();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.radioButton){
            if(test != 1){
                progressBar.incrementProgressBy(1);
                test = 1;
                count++;
                count();
            }
        }else if (v.getId() == R.id.radioButton2){
            if(test != 1){
                progressBar.incrementProgressBy(1);
                test = 1;
                count++;
                count();
            }
        }

        if(v.getId() == R.id.button){
            Toast.makeText(this, "안녕?", Toast.LENGTH_SHORT).show();
        }
    }
}
