package com.georgster.csci4810.util;

public class Dataline {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int[] start;
    private int[] end;

    public Dataline(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start = new int[] { x1, y1, 1 };
        this.end = new int[] { x2, y2, 1 };
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
        this.start[0] = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
        this.end[0] = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
        this.start[1] = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
        this.end[1] = y2;
    }

    public int[] getStart() {
        return start;
    }

    public void setStart(int[] start) {
        this.start = start;
        this.x1 = start[0];
        this.y1 = start[1];
    }

    public int[] getEnd() {
        return end;
    }

    public void setEnd(int[] end) {
        this.end = end;
        this.x2 = end[0];
        this.y2 = end[1];
    }

    public String toString() {
        return "x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2;
    }
}
