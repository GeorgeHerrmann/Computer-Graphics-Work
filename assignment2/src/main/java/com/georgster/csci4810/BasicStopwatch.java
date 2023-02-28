package com.georgster.csci4810;

public class BasicStopwatch {
    long timeElapsed; // in milliseconds
    long startTime; // in milliseconds
    long endTime; // in milliseconds

    public BasicStopwatch() {
        timeElapsed = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        timeElapsed = 0;
        endTime = 0;
    }

    public void stop() {
        endTime = System.currentTimeMillis();
        timeElapsed = endTime - startTime;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }
}
