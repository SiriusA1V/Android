package com.andrstudy.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private ArrayList<HistoryBean> data;

    private void generateDummyData(){
        this.data = new ArrayList<>();

        for(int i = 0; i < 20 ;  i++){
            HistoryBean bean = new HistoryBean();
            bean.setId(i+1);
            bean.setName("Name:"+(i+1));
            data.add(bean);
        }
    }
    public HistoryAdapter(ArrayList<HistoryBean> data){
        if(data == null) generateDummyData();
        else this.data = data;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        HistoryBean bean = data.get(position);
        holder.textViewId.setText(String.valueOf(bean.getId()));
        holder.textViewName.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        if(data == null) return 0;
        else return data.size();
    }
}
