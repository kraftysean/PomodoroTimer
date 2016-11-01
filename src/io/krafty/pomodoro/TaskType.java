package io.krafty.pomodoro;

/**
 * Created by sean on 01/11/16.
 */
public enum TaskType {
    POMODORO,
    SHORT_BREAK,
    LONG_BREAK;

    @Override
    public String toString() {
        String temp = super.toString().toLowerCase();
        temp = temp.replace('_', ' ');
        return temp.replace(temp.charAt(0), Character.toUpperCase(temp.charAt(0)));
    }
}
