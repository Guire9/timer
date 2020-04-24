package edu.temple.timer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView timerDisplay;
    TimerService.TimerBinder binder;

    boolean connected;
    ServiceConnection connection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (TimerService.TimerBinder) service;
            connected = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerDisplay= findViewById(R.id.timerDisplay);

        Intent bindIntent = new Intent(this,TimerService.class);
        bindService(bindIntent,connection,BIND_AUTO_CREATE);

        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connected){
                    binder.start30SecondTimer(timerDisplay);
                }
            }
        });
    }
}

