package com.andrstudy.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity{
    private HistoryAdapter adapter;
    private RecyclerView list;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_history);

        adapter = new HistoryAdapter(null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list = (RecyclerView)findViewById(R.id.list);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }
}
