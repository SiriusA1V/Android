package kr.ac.yjc.wdj.networks2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by siri on 2018-01-19.
 */

public class StationInfoAdapter extends BaseAdapter {
    Context context;
    ArrayList<StationInfo> total_stationinfo = new ArrayList<StationInfo>();

    public StationInfoAdapter(Context context, ArrayList<StationInfo> total_stationinfo) {
        this.context = context;
        this.total_stationinfo = total_stationinfo;
    }

    @Override
    public int getCount() {
        return total_stationinfo.size();
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

        layout.addView(t1);
        layout.addView(t2);

        StationInfo stationInfo = total_stationinfo.get(i);
        t1.setText(stationInfo.name);
        t2.setText(stationInfo.id);

        return layout;
    }
}
