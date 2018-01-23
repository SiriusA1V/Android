package kr.ac.yjc.wdj.networks2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

        import net.htmlparser.jericho.Element;
        import net.htmlparser.jericho.Source;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends ListActivity {
    TextView tv;
    String result = "";
    String id = "";    //정류장 번호
    List<BussArrinfo> total_arrInfo = new ArrayList<BussArrinfo>();

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            BusArrInfoAdapter adapter = new BusArrInfoAdapter(MainActivity.this, total_arrInfo);
            setListAdapter(adapter);
        }
    };

    Thread myThread = new Thread(){
        @Override
        public void run() {

            try {
                URL url = new URL("http://businfo.daegu.go.kr/ba/arrbus/arrbus.do?act=bsArr&bsNm="+id);
            /*URL url = new URL("http://businfo.daegu.go.kr/ba/arrbus/arrbus.do?act=bsArr&bs");*/

                InputStream is = url.openConnection().getInputStream();

                Reader r = new InputStreamReader(is, "euc-kr");    //바이트 스트링을 케릭터 스트링으로

                Source src = new Source(r);
                src.fullSequentialParse();//소스에 모든 태그를 객체화 (Element tag)
                src.getAllElements();
                List<Element> all = src.getAllElements(); //모든 엘리먼트 객체를 가져오기

                BussArrinfo arrInfo = null;

                for(int i=0; i<all.size(); i++){
                    Element temp = all.get(i);

                    if(temp.getName().equals("span")){
                        String str = temp.getAttributeValue("class"); //span태그 class 속성

                        switch (str){
                            case "route_no":
                                arrInfo = new BussArrinfo();
                                arrInfo.setRoute_no(temp.getTextExtractor().toString());
                                break;
                            case "arr_state":
                                arrInfo.setArr_state(temp.getTextExtractor().toString());
                                break;
                            case "cur_pos":
                                arrInfo.setCur_pos(temp.getTextExtractor().toString());
                                total_arrInfo.add(arrInfo);
                                break;
                        }
                    }
                }

                myHandler.sendEmptyMessage(0);



            } catch (Exception e) { //모든 Exception
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        myThread.start();
    }
}