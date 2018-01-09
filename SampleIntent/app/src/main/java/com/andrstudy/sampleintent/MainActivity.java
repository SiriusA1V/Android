package com.andrstudy.sampleintent;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;    // 다른 액티비티를 띄우기 위한 요청코드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v){
        /*Intent intent = new Intent(getApplicationContext(), MenuActivity.class); //(현액티비티호출함수 or this,대상액티비티)*/

        Intent intent = new Intent();

        ComponentName name = new ComponentName("com.andrstudy.sampleintent", "com.andrstudy.sampleintent.MenuActivity");    // 20번줄을 상대경로로
        intent.setComponent(name);

        startActivityForResult(intent, REQUEST_CODE_MENU);  // 액티비티 띄우기
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 응답 처리 메소드(대상액티비티의 코드, 응답코드, intent(데이트터를 갖고있는))
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(), "onActivityResult 메소드 호출됨. 요청 코드 : " + requestCode + ", 결과 코드 : " + resultCode,
                    Toast.LENGTH_SHORT).show();
            if(resultCode == RESULT_OK){
                String name = data.getExtras().getString("name");   // 다음 액티비티에서 보내온 값 반환
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
