package kr.ac.yjc.wdj.customviewex;

import android.graphics.drawable.Drawable;

/**
 * Created by jungyu on 2018-01-17.
 */

//리스트뷰 한줄에 공급될 데이터의 묶음
public class MyData {
    public MyData(Drawable icon, String[] strs) {
        this.icon = icon;
        this.strs = strs;
    }

    private Drawable icon;
    private String  strs[]  =   new String[3];


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }
}