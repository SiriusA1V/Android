package com.andrstudy.pmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static MediaPlayer backMusic;
    private Button button1, button2, button3;
    private SeekBar seekbar;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backMusic = MediaPlayer.create(this, R.raw.chuuk);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        seekbar = (SeekBar)findViewById(R.id.seekBar2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button1.getId()){
            new AsyncTask<MediaPlayer, Integer, String>(){

                @Override
                protected String doInBackground(MediaPlayer... params) {
                    playB();
                    seekM();
                    return null;
                }
            }.execute(backMusic);
        }

        if(v.getId() == button2.getId()){
            backMusic.pause();
        }
        
        if(v.getId() == button3.getId()){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 11111);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 11111){
            if(resultCode == RESULT_OK){
                uri = data.getData();
            }
        }

        /*try {
            backMusic.setDataSource(getApplicationContext(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void seekM(){
        while (backMusic.isPlaying()){
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }

            seekbar.setProgress(backMusic.getCurrentPosition());
        }
    }

    public void playB(){
        seekbar.setMax(backMusic.getDuration());
        backMusic.start();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    backMusic.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
