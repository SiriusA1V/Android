package kr.ac.yjc.wdj.networks2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by siri on 2018-01-18.
 */

class StationInfo{
    String name = "";
    String id = "";
}

public class ReqStationActivity extends Activity {
    EditText addr;
    Button request;
    ListView listView;

    ArrayList<StationInfo> total_stationinfo = new ArrayList<StationInfo>();

    Handler myhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            StationInfoAdapter stationInfoAdapter = new StationInfoAdapter(ReqStationActivity.this, total_stationinfo);
            listView.setAdapter(stationInfoAdapter);

            request.setEnabled(true);
            request.setText("정류장 검색하기");
        }
    };

    class MyThread extends Thread{
        @Override
        public void run() {
            try{
                URL url = new URL("http://businfo.daegu.go.kr/ba/arrbus/arrbus.do");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setDoOutput(true);
                String parameter = "act=findByBusStopNo&bsNm=+addr.getText()";
                OutputStream os = con.getOutputStream();
                os.write( parameter.getBytes("euc-kr") );
                os.flush();

                InputStream is = con.getInputStream();

                Reader r = new InputStreamReader(is, "euc-kr");    //바이트 스트링을 케릭터 스트링으로

                Source src = new Source(r);
                boolean flag = true;    //true : 정류장 name, false : 정류장 id
                src.fullSequentialParse();//소스에 모든 태그를 객체화 (Element tag)
                src.getAllElements();
                List<Element> all = src.getAllElements(); //모든 엘리먼트 객체를 가져오기

                StationInfo stationInfo = null;
                for(int i = 0; i < all.size(); i++){
                    Element temp = all.get(i);
                    if(temp.getName().equals("td")){
                        if(flag == true){
                            stationInfo = new StationInfo();
                            stationInfo.name = temp.getTextExtractor().toString();
                            flag = false;
                        }else{
                            stationInfo.id = temp.getTextExtractor().toString();
                            total_stationinfo.add(stationInfo);
                            flag = true;
                        }
                        Log.d("###########", temp.getTextExtractor().toString());
                    }
                }   //for end

                myhandler.sendEmptyMessage(0);

            }catch (Exception e){
                Log.d("Network err", e.toString());
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reqstationactivity);

        addr = (EditText)findViewById(R.id.editText01);
        request = (Button)findViewById(R.id.button01);
        listView = (ListView)findViewById(R.id.listView01);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout layout = (LinearLayout)view;
                TextView t = (TextView)layout.getChildAt(1);
                String id = t.getText().toString();    //정류장 번호

                Intent intent = new Intent(ReqStationActivity.this, MainActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request.setText("요청중...");
                request.setEnabled(false);

                //기존 ListView에 연동된 데이터 삭제
                listView.setAdapter(null);
                total_stationinfo.clear();

                // 특정 View에 의해 생성된 키보드 감추기
                InputMethodManager im = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(addr.getWindowToken(), 0);

                new MyThread().start();
            }
        });

    }
}
