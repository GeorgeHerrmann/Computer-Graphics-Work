package com.georgster.csci4810.util;

/**
 * A class to represent the starting and ending podoubles of a line.
 */
public class Dataline {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double z1;
    private double z2;
    private double[] start;
    private double[] end;

    /**
     * Creates a new Dataline with the given starting and ending podoubles.
     * 
     * @param x1 The x coordinate of the starting podouble
     * @param y1 The y coordinate of the starting podouble
     * @param x2 The x coordinate of the ending podouble
     * @param y2 The y coordinate of the ending podouble
     */
    public Dataline(double x1, double y1, double x2, double y2, double z1, double z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
        this.start = new double[] { x1, y1, z1, 1 };
        this.end = new double[] { x2, y2, z2, 1 };
    }

    /**
     * Returns the x coordinate of the starting podouble.
     * 
     * @return The x coordinate of the starting podouble
     */
    public double getX1() {
        return x1;
    }

    /**
     * Sets the x coordinate of the starting podouble.
     * 
     * @param x1 The x coordinate of the starting podouble
     */
    public void setX1(double x1) {
        this.x1 = x1;
        this.start[0] = x1;
    }

    /**
     * Returns the x coordinate of the ending podouble.
     * 
     * @return The x coordinate of the ending podouble
     */
    public double getX2() {
        return x2;
    }

    /**
     * Sets the x coordinate of the ending podouble.
     * 
     * @param x2 The x coordinate of the ending podouble
     */
    public void setX2(double x2) {
        this.x2 = x2;
        this.end[0] = x2;
    }

    /**
     * Returns the y coordinate of the starting podouble.
     * 
     * @return The y coordinate of the starting podouble
     */
    public double getY1() {
        return y1;
    }

    /**
     * Sets the y coordinate of the starting podouble.
     * 
     * @param y1 The y coordinate of the starting podouble
     */
    public void setY1(double y1) {
        this.y1 = y1;
        this.start[1] = y1;
    }

    /**
     * Returns the y coordinate of the ending podouble.
     * 
     * @return The y coordinate of the ending podouble
     */
    public double getY2() {
        return y2;
    }

    /**
     * Sets the y coordinate of the ending podouble.
     * 
     * @param y2 The y coordinate of the ending podouble
     */
    public void setY2(double y2) {
        this.y2 = y2;
        this.end[1] = y2;
    }

    /**
     * Returns the starting podouble as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @return The starting podouble as a 1x3 array
     */
    public double[] getStart() {
        return start;
    }

    /**
     * Sets the starting podouble as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @param start The starting podouble as a 1x3 array
     */
    public void setStart(double[] start) {
        this.start = start;
        this.x1 = start[0];
        this.y1 = start[1];
        this.z1 = start[2];
    }

    /**
     * Returns the ending podouble as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @return The ending podouble as a 1x3 array
     */
    public double[] getEnd() {
        return end;
    }

    /**
     * Sets the ending podouble as a 1x3 array in
     * the form [x, y, 1].
     * 
     * @param end The ending podouble as a 1x3 array
     */
    public void setEnd(double[] end) {
        this.end = end;
        this.x2 = end[0];
        this.y2 = end[1];
        this.z2 = end[2];
    }

    /**
     * Returns the z coordinate of the starting podouble.
     * 
     * @return The z coordinate of the starting podouble
     */
    public double getZ1() {
        return z1;
    }

    /**
     * Sets the z coordinate of the starting podouble.
     * 
     * @param z1 The z coordinate of the starting podouble
     */
    public void setZ1(double z1) {
        this.z1 = z1;
        this.start[2] = z1;
    }

    /**
     * Returns the z coordinate of the ending podouble.
     * 
     * @return The z coordinate of the ending podouble
     */
    public double getZ2() {
        return z2;
    }

    /**
     * Sets the z coordinate of the ending podouble.
     * 
     * @param z2 The z coordinate of the ending podouble
     */
    public void setZ2(double z2) {
        this.z2 = z2;
        this.end[2] = z2;
    }

    /**
     * Returns a string representation of the Dataline.
     * 
     * @return A string representation of the Dataline
     */
    @Override
    public String toString() {
        return "Dataline [x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2 + ", z1=" + z1 + ", z2=" + z2 + "]";
    }
}
