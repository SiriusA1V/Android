package com.andrstudy.data;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout[] layout;
    private EditText editTextName;
    private TextView textViewName;
    private final int LOGIN = 0;
    private final int LOGOUT = 1;
    private final int COUNT=2;
    private SharedPreferences preference;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = new LinearLayout[COUNT];

        layout[LOGIN] = (LinearLayout)findViewById(R.id.layoutLogin);
        layout[LOGOUT] = (LinearLayout)findViewById(R.id.layoutLogout);
        editTextName = (EditText)findViewById(R.id.editTextName);
        textViewName = (TextView)findViewById(R.id.textViewName);

        preference = getSharedPreferences("namePref", Activity.MODE_PRIVATE);

        String name = preference.getString("name", null);
        int g_count = preference.getInt("count", 0);
        if(name == null) showLayout(LOGIN);
        else{
            textViewName.setText(name+g_count);
            showLayout(LOGOUT);
        }
    }

    private void showLayout(int index)
    {
        for(int i=0; i < COUNT; i++){
            if(i == index) layout[i].setVisibility(View.VISIBLE);
            else layout[i].setVisibility(View.GONE);
        }
    }

    public void onLogin(View view){
        if(preference == null)
            return;
        if(editTextName.getText().toString().length() == 0)
            return;


        SharedPreferences.Editor editor = preference.edit();
        editor.putString("name", editTextName.getText().toString());
        textViewName.setText(editTextName.getText().toString());
        showLayout(LOGOUT);

        editor.putInt("count", ++count);

        editor.commit();
    }

    public void onLogout(View view){
        if(preference == null)
            return;

        SharedPreferences.Editor editor = preference.edit();
        editor.remove("name");
        editor.commit();
        showLayout(LOGIN);
    }

    public void onHistory(View view){
        startActivity(new Intent(this, HistoryActivity.class));
    }
}
