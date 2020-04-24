package edu.temple.timer;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;


import androidx.annotation.Nullable;


public class TimerService extends Service {
    public TimerService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new TimerBinder();
    }

    class TimerBinder extends Binder {
        void start30SecondTimer(TextView timerDisplay) {
            {
                startTimer(30, timerDisplay);
            }
        }

        public void startTimer(final int timer, final TextView timerDisplay) {
            Thread t = new Thread() {
                public void run() {
                    for (int i = timer; i >= 0; i--) {
                        Log.i("Countdown: ", String.valueOf(i));
                        timerDisplay.setText(String.valueOf(i));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();
        }
    }
}
