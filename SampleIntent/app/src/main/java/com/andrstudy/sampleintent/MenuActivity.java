package com.andrstudy.sampleintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               Intent intent = new Intent();
               intent.putExtra("name", "ㅅㅂ?"); // 다음 액티비티로 값 전달(키, 값)
               setResult(RESULT_OK, intent);    // 이전 액티비티로 인텐트 전달(응답 코드(사용자지정가능), 인텐트)

               finish();    //현재 액티비티 종료
           }
        });
    }
}