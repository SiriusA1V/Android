package kr.ac.yjc.wdj.customviewex;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by siri on 2018-01-17.
 */

public class MyLinearLayout extends LinearLayout {
    LinearLayout left;
    TextView t1, t2, t3;    //원칙상 프라이빗 해서 겟셋해 줘야한다.
    MyData data;

    public MyLinearLayout(Context context, MyData data) {
        super(context);
        this.data = data;

        //line.xml 객체화

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.line, this, true);

        left = (LinearLayout)findViewById(R.id.left);
        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        t3 = (TextView)findViewById(R.id.t3);

        setData(data);
    }

    public void setData(MyData data) {
        left.setBackgroundDrawable(data.getIcon());
        String strs[] = data.getStrs();
        t1.setText(strs[0]);
        t2.setText(strs[1]);
        t3.setText(strs[2]);
    }
}
