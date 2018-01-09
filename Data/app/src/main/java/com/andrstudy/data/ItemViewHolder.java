package com.andrstudy.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewId;
    TextView textViewName;
    public ItemViewHolder(View itemView) {
        super(itemView); textViewId = (TextView)itemView.findViewById(R.id.textViewId);
        textViewName = (TextView)itemView.findViewById(R.id.textViewName);
    }
}