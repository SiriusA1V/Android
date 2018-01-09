package com.andrstudy.httpconnect;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editText1, editText2;
    Button button;
    ImageView imageView1, imageView2;
    String post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        button.setVisibility(View.INVISIBLE);
        post = "userid="+editText1.getText().toString()+"&password="+editText2.getText().toString();

        try{
            URL url = new URL("http://172.19.2.176/public/test2/test2.php");
            new AsyncTask<URL, Integer, String>(){

                protected void onProgressUpdate(Integer... values){
                    super.onProgressUpdate(values);
                    if(values.length > 0)
                        Log.i("http", String.valueOf(values[0]));
                }

                protected void onPreExecute(){super.onPreExecute();}

                @Override
                protected String doInBackground(URL... params) {
                    String result = new String();
                    int i = 0;

                    if(params == null || params.length < 1)
                        return null;
                    try{
                        publishProgress(i++);
                        HttpURLConnection connection = (HttpURLConnection)params[0].openConnection();
                        Log.i("http", "connected");

                        //post형식
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);

                        //connection.setRequestMethod("GET");
                        connection.setDoInput(true);
                        connection.setUseCaches(false);
                        connection.setDefaultUseCaches(false);

                        //post 형식
                        OutputStream os = connection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                        writer.write(post);
                        writer.flush();

                        publishProgress(i++);
                        InputStream is = connection.getInputStream();
                        StringBuilder builder = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        String line;
                        while((line = reader.readLine()) != null){
                            builder.append(line+ "\n");
                            publishProgress(i++);
                        }
                        result = builder.toString();Log.i("http", "result=" + result);
                        publishProgress(i++);

                        is.close();
                        os.close();
                        connection.disconnect();
                    }catch(IOException me){
                        me.printStackTrace();
                    }

                    return result;
                }

                protected void onPostExecute(String s){
                    super.onPostExecute(s);

                    button.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

                    try {
                        JSONArray arr = new JSONArray(s);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }.execute(url);

        }catch (MalformedURLException e){

        }
    }
}
