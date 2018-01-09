package com.andrstudy.permission2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements ToggleButton.OnCheckedChangeListener, OnMessage{
    private ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (ToggleButton)findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(this);


        /*MyReceiver receiver = new MyReceiver(this);*/
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }else{

        }
    }

    @Override
    public void onMessage(int state) {

    }
}

interface OnMessage{
    public void onMessage(int state);
}

class MyReceiver extends BroadcastReceiver {
    private OnMessage handler;

    public MyReceiver(OnMessage handler){
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);

        switch (state){
            case BluetoothAdapter.STATE_ON:
                break;
            case BluetoothAdapter.STATE_OFF:
                break;
        }
    }
}