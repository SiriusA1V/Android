package kr.ac.yjc.wdj.networks2;

/**
 * Created by siri on 2018-01-18.
 */

public class BussArrinfo  {
    private String route_no = "";
    private String arr_state = "";
    private String cur_pos = "";

    public BussArrinfo() {}

    public BussArrinfo(String route_no, String arr_state, String cur_pos) {
        this.route_no = route_no;
        this.arr_state = arr_state;
        this.cur_pos = cur_pos;
    }

    public String getRoute_no() {
        return route_no;
    }

    public String getArr_state() {
        return arr_state;
    }

    public String getCur_pos() {
        return cur_pos;
    }

    public void setRoute_no(String route_no) {
        this.route_no = route_no;
    }

    public void setArr_state(String arr_state) {
        this.arr_state = arr_state;
    }

    public void setCur_pos(String cur_pos) {
        this.cur_pos = cur_pos;
    }
}
