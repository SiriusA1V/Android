package kr.ac.yjc.wdj.customviewex;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jungyu on 2018-01-17.
 */
//리스트의 한줄한줄을 만들어줌
public class MyAdapter extends BaseAdapter {
    private ArrayList<MyData> total = new ArrayList<MyData>();
    private Context con;

    public MyAdapter(Context con,ArrayList<MyData> total) {
        this.con    = con;
        this.total  = total;
    }

    public int getCount() {
        return total.size();
        //보여 주게될 줄 수를 리턴
        //일반적으로 데이터 사이즈 리턴
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    //int i 의 의미 몇번째 줄인지 알려줌
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyData data = total.get(i);
        MyLinearLayout layout;

        if(view == null){
            layout = new MyLinearLayout(con, data);
        }else {
            layout = (MyLinearLayout) view;
            layout.setData(data
            );
        }

        return layout;
    }
}