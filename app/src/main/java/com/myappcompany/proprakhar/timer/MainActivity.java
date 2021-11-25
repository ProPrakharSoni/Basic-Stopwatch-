package com.myappcompany.proprakhar.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean state=true ;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    Button button;
    int current=30;
    public void start(View view) {
         button = findViewById(R.id.button);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView textView = (TextView) findViewById(R.id.textView);
        if (state) {
            button.setText("Stop");
            seekBar.setEnabled(false);
            state = false;
            seekBar.setAlpha(0.5f);
            countDownTimer=new CountDownTimer(seekBar.getProgress()*1000+500, 1000) {
                @Override
                public void onTick(long l) {
                    Log.i("sec left",String.valueOf(l/1000)) ;
                    int min,sec,c=(int)l/1000;
                    min=c/60;
                    sec=c-(min*60);
                    if(sec>=0&&sec<=9)
                        textView.setText(Integer.toString(min)+":0"+Integer.toString(sec));
                    else
                        textView.setText(Integer.toString(min)+":"+Integer.toString(sec));
                }
                @Override
                public void onFinish() {
                    mediaPlayer.start();
                    seekBar.setEnabled(true);
                    seekBar.setProgress(30);
                    textView.setText("0:30");
                    seekBar.setAlpha(1);
                    button.setText("Go");
                    state = true;
                }
            }.start();  //compiler yaha se start karke aage(next line) execute karne lag jata hai.
            Log.i("sec leftaman",String.valueOf(current)) ;
        } else {
            seekBar.setEnabled(true);
            button.setText("Go");
            state = true;
            seekBar.setAlpha(1);
            countDownTimer.cancel();
            textView.setText("0:30");
            seekBar.setProgress(30);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this,R.raw.airhorne);
        SeekBar seekBar =(SeekBar) findViewById(R.id.seekBar);
        final TextView textView =(TextView) findViewById(R.id.textView);
        seekBar.setProgress(30);
        seekBar.setMax(600);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                current=i;
                int min,sec;
                min=i/60;
                sec=i-(min*60);
                if(sec>=0&&sec<=9)
                    textView.setText(Integer.toString(min)+":0"+Integer.toString(sec));
                else
                    textView.setText(Integer.toString(min)+":"+Integer.toString(sec));
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