package com.example.dell.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {
    private int seconds=0;
    private boolean running;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        if (savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasrunning", wasrunning);
    }

    @Override
    protected void onStop(){
        super.onStop();
        wasrunning=running;
        if (wasrunning=true){
            running=true;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(wasrunning){
            running=true;
        }
    }
    public void onClickStart(View view){
        running=true;
    }
    public void onClickStop(View view){
        running=false;
    }
    public void onClickReset(View view){
        running=false;
        seconds=0;
    }

    private void runTimer(){
        final TextView timeview= (TextView)findViewById(R.id.time_view);
        final Handler handler= new Handler();
        handler.post(new Runnable(){
           @Override
            public void run(){
               int hours = seconds/3600;
               int minutes= (seconds%3600)/60;
               int secs= seconds%60;
               String time= String.format("%d:%02d:%02d", hours, minutes, secs);
               timeview.setText(time);
               if(running){
                   seconds++;
               }
               handler.postDelayed(this, 1000);
           }
        });
    }
}
