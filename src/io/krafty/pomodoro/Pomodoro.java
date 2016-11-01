package io.krafty.pomodoro;

import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import static io.krafty.pomodoro.TaskType.*;

public class Pomodoro {

    final Timer timer;
    LocalTime pomTask, shortBreak, longBreak;
    int loops;
    static int delay;

    Pomodoro(int loops, int pomTask, int shortBreak, int longBreak) {
        timer = new Timer();
        this.loops = loops;
        this.pomTask = LocalTime.of(0, pomTask);
        this.shortBreak = LocalTime.of(0, shortBreak);
        this.longBreak = LocalTime.of(0, longBreak);
        scheduleTasks();
    }

    void scheduleTasks() {
        for (int i = 0; i < loops; i++) {
            timer.scheduleAtFixedRate(new PomodoroTask(POMODORO, pomTask), delay, 1000);
            delay += pomTask.toSecondOfDay() * 1000;
            if((i + 1) < loops) {
                timer.scheduleAtFixedRate(new PomodoroTask(SHORT_BREAK, shortBreak), delay, 1000);
                delay += shortBreak.toSecondOfDay() * 1000;
            }
            else
                timer.scheduleAtFixedRate(new PomodoroTask(LONG_BREAK, longBreak), delay, 1000);
        }

    }

    class PomodoroTask extends TimerTask {
        TaskType label;
        int secondsRemaining;

        PomodoroTask(TaskType label, LocalTime duration) {
            this.label = label;
            secondsRemaining = duration.toSecondOfDay();
        }

        @Override
        public void run() {
            if(secondsRemaining != 0)
                System.out.printf("\r%02d:%02d", secondsRemaining / 60, secondsRemaining % 60);  // Print time on same line
            secondsRemaining--;
            if (secondsRemaining < 0) {
                this.cancel();
                Toolkit.getDefaultToolkit().beep();  // Not compatible with Linux
                System.out.println("\r" + label.toString() + " over");

                if(label.equals(LONG_BREAK)) {
                    timer.cancel();
                    System.out.println("Session ended");
                }
            }
        }
    }

    public static void main(String[] args) {
        if(args.length == 0)
            new Pomodoro(2, 25, 5, 15);
        else if(args.length == 1)
            new Pomodoro(Integer.parseInt(args[0]), 25, 5, 15);
        else if(args.length == 2)
            new Pomodoro(Integer.parseInt(args[0]), Integer.parseInt(args[1]), 5 , 15);
        else if(args.length == 3)
            new Pomodoro(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), 15);
        else if(args.length == 4)
            new Pomodoro(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        else
            System.out.println("Invalid number of arguments entered");
    }
}
