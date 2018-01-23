package kr.ac.yjc.wdj.networks;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    byte[] bText;

    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {    //callback method
            //main thread에 의해서 실행

            tv.setText(new String(bText));  //화면 갱신은 main Thread에서만 가능
        }
    }

    MyHandler myHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv = new TextView(this);
        setContentView(tv);

        new Thread(){
            public void run(){
                try{
                    URL text = new URL("http://www.google.com");
                    InputStream isText = text.openStream();

                    //가져올 내용을 저장할 공간
                    bText = new byte[256];
                    int readSize = isText.read(bText);
                    Log.i("Net", "readSize = "  + readSize);
                    Log.i("Net", "bText = " + new String(bText));

                    /*tv.setText(new String(bText));*/      //화면 갱신은 main THread 에서만 사용 가능
                    myHandler.sendEmptyMessage(0);    //main Thread 리드

                    isText.close();
                }catch (Exception e){
                    Log.e("Net", "Error in network call", e);
                }
            }
        }.start();
    }
}
