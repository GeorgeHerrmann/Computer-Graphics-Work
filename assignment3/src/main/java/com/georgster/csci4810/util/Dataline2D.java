package com.georgster.csci4810.util;

/**
 * A class to represent the starting and ending points of a line.
 */
public class Dataline2D {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int[] start;
    private int[] end;

    /**
     * Creates a new Dataline with the given starting and ending points.
     * 
     * @param x1 The x coordinate of the starting point
     * @param y1 The y coordinate of the starting point
     * @param x2 The x coordinate of the ending point
     * @param y2 The y coordinate of the ending point
     */
    public Dataline2D(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start = new int[] { x1, y1, 1 };
        this.end = new int[] { x2, y2, 1 };
    }

    /**
     * Returns the x coordinate of the starting point.
     * 
     * @return The x coordinate of the starting point
     */
    public int getX1() {
        return x1;
    }

    /**
     * Sets the x coordinate of the starting point.
     * 
     * @param x1 The x coordinate of the starting point
     */
    public void setX1(int x1) {
        this.x1 = x1;
        this.start[0] = x1;
    }

    /**
     * Returns the x coordinate of the ending point.
     * 
     * @return The x coordinate of the ending point
     */
    public int getX2() {
        return x2;
    }

    /**
     * Sets the x coordinate of the ending point.
     * 
     * @param x2 The x coordinate of the ending point
     */
    public void setX2(int x2) {
        this.x2 = x2;
        this.end[0] = x2;
    }

    /**
     * Returns the y coordinate of the starting point.
     * 
     * @return The y coordinate of the starting point
     */
    public int getY1() {
        return y1;
    }

    /**
     * Sets the y coordinate of the starting point.
     * 
     * @param y1 The y coordinate of the starting point
     */
    public void setY1(int y1) {
        this.y1 = y1;
        this.start[1] = y1;
    }

    /**
     * Returns the y coordinate of the ending point.
     * 
     * @return The y coordinate of the ending point
     */
    public int getY2() {
        return y2;
    }

    /**
     * Sets the y coordinate of the ending point.
     * 
     * @param y2 The y coordinate of the ending point
     */
    public void setY2(int y2) {
        this.y2 = y2;
        this.end[1] = y2;
    }

    /**
     * Returns the starting point as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @return The starting point as a 1x3 array
     */
    public int[] getStart() {
        return start;
    }

    /**
     * Sets the starting point as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @param start The starting point as a 1x3 array
     */
    public void setStart(int[] start) {
        this.start = start;
        this.x1 = start[0];
        this.y1 = start[1];
    }

    /**
     * Returns the ending point as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @return The ending point as a 1x3 array
     */
    public int[] getEnd() {
        return end;
    }

    /**
     * Sets the ending point as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @param end The ending point as a 1x3 array
     */
    public void setEnd(int[] end) {
        this.end = end;
        this.x2 = end[0];
        this.y2 = end[1];
    }

    /**
     * Returns a string representation of the Dataline.
     * 
     * @return A string representation of the Dataline
     */
    @Override
    public String toString() {
        return "x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2;
    }
}
