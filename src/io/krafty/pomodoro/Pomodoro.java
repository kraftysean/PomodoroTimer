package io.krafty.pomodoro;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sean on 29/10/16.
 */
public class Pomodoro {

    public static void main(String[] args) {
        final Timer timer = new Timer();
        LocalTime tCounter = LocalTime.of(0, 0, 5);
        timer.scheduleAtFixedRate(new TimerTask() {
            int secondsRemaining = tCounter.toSecondOfDay();
            @Override
            public void run() {
                System.out.printf("%02d:%02d\n", secondsRemaining / 60, secondsRemaining % 60);
                if(--secondsRemaining < 0) {
                    timer.cancel();
                    System.out.println("Timer Ended");
                }

            }
        }, 0, 1000);
    }
}
