package io.krafty.pomodoro;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sean on 29/10/16.
 */
public class Pomodoro {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
        long delay = 0L;
        long period = 0L;
        timer.scheduleAtFixedRate(task, delay, period);
    }
}
