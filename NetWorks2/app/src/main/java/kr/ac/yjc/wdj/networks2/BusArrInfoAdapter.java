package kr.ac.yjc.wdj.networks2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siri on 2018-01-18.
 */

public class BusArrInfoAdapter extends BaseAdapter{
    List<BussArrinfo> total_arrInfo;
    Context context;

    public BusArrInfoAdapter(Context context, List<BussArrinfo> total_arrInfo) {
        this.context = context;
        this.total_arrInfo = total_arrInfo;
    }

    @Override
    public int getCount() {
        return total_arrInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        TextView t1 = new TextView(context);
        TextView t2 = new TextView(context);
        TextView t3 = new TextView(context);
        layout.addView(t1);
        layout.addView(t2);
        layout.addView(t3);

        LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);  //width height weight
        t1.setLayoutParams(params);
        t2.setLayoutParams(params);
        t3.setLayoutParams(params);

        BussArrinfo temp = total_arrInfo.get(i);
        t1.setText(temp.getRoute_no());
        t2.setText(temp.getArr_state());
        t3.setText(temp.getCur_pos());

        return layout;
    }
}
