package com.georgster.csci4810.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.georgster.csci4810.util.Dataline;
import com.georgster.csci4810.util.Dataline2D;
import com.georgster.csci4810.util.MatrixOperations;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

public class Transformer3D {
    private GraphicsContext gc;
    private List<Dataline> datalines;
    private double[] viewpoint;
    private double zPlane;
    private double screenSize;
    private double screenDistance;
    private double xe;
    private double ye;
    private double v;
    private static int n = 1024;

    public Transformer3D(GraphicsContext gc, double[] viewpoint, double zPlane, double screenSize, double screenDistance) {
        this.gc = gc;
        this.viewpoint = viewpoint;
        this.zPlane = zPlane;
        this.screenSize = screenSize;
        this.screenDistance = screenDistance;
        datalines = new ArrayList<>();
        
        v = 511.5;
        xe = calculateXe();
        ye = calculateYe();

        System.out.println("v: " + v);
        System.out.println("xe: " + xe);
        System.out.println("ye: " + ye);
    }

    public void addDataline(Dataline dataline) {
        datalines.add(dataline);
    }

    private double calculateXe() {
        return (screenSize / 2) * (1 + (viewpoint[0] / zPlane));
    }

    private double calculateYe() {
        return (screenSize / 2) * (1 + (viewpoint[1] / zPlane));
    }

    private double calculateVValue() {
        double theta = 2 * Math.atan(screenSize / (2 * screenDistance));
        return Math.tan(theta/2);
    }

    public void applyTranslation(double tx, double ty, double tz) {
        double[][] translationMatrix = new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {tx, ty, tz, 1}
        };

        datalines = applyTransformation(translationMatrix);
    }

    public void transformPoints() {
        Platform.runLater(() -> gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()));
        for (Dataline line : applyTransformation(getTransformationMatrix())) {
            System.out.println("3D: " + line.toString());
            Dataline2D dataline2d = new Dataline2D(0, 0, 0, 0);

            double x = (double) line.getX1();
            double y = (double) line.getY1();
            double z = (double) line.getZ1();

            int xs = (int) Math.round(((x / z) * v) + v);
            int ys = (int) Math.round(((y / z) * v) + v);

            dataline2d.setStart(new int[]{xs, ys, 1});

            x = (double) line.getX2();
            y = (double) line.getY2();
            z = (double) line.getZ2();

            xs = (int) Math.round(((x / z) * v) + v);
            ys = (int) Math.round(((y / z) * v) + v);

            dataline2d.setEnd(new int[]{xs, ys, 1});

            System.out.println("2D: " + dataline2d.toString());

            brz2(dataline2d);
        }
    }

    /**
     * Draws the given Dataline on the GraphicsContext using the Bresenham algorithm.
     * 
     * @param line The line to draw
     */
    private void brz2(Dataline2D line) {
        int x0 = line.getX1();
        int y0 = line.getY1();
        int x1 = line.getX2();
        int y1 = line.getY2();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int e2;
    
        while (true) {
          drawPoint(x0, y0);
          if (x0 == x1 && y0 == y1) break;
          e2 = err + err;
          if (e2 > -dy) {
            err = err - dy;
            x0 = x0 + sx;
          }
          if (e2 < dx) {
            err = err + dx;
            y0 = y0 + sy;
          }
        }
    }

    private double[][] getTransformationMatrix() {
        double[][] matrix = MatrixOperations.matrixMultiplication(getVMatrix(), getNMatrix());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 2) {
                    matrix[i][j] *= -1;
                }
                BigDecimal bd2 = BigDecimal.valueOf(matrix[i][j]);
                bd2 = bd2.setScale(1, RoundingMode.HALF_UP);
                matrix[i][j] = bd2.doubleValue();
            }
        }
        return matrix;
    }

    private double[][] getVMatrix() {
        double[][] t1 = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {-viewpoint[0], -viewpoint[1], -viewpoint[2], 1}
        };

        double[][] t2 = {
            {1, 0, 0, 0},
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 1}
        };

        double magnitude1 = ye / Math.sqrt(Math.pow(xe, 2) + Math.pow(ye, 2));
        double magnitude2 = xe / Math.sqrt(Math.pow(xe, 2) + Math.pow(ye, 2));

        BigDecimal bd1 = BigDecimal.valueOf(magnitude1);
        bd1 = bd1.setScale(1, RoundingMode.HALF_UP);
        magnitude1 = bd1.doubleValue();

        BigDecimal bd2 = BigDecimal.valueOf(magnitude2);
        bd2 = bd2.setScale(1, RoundingMode.HALF_UP);
        magnitude2 = bd2.doubleValue() - 0.1;

        System.out.println("magnitude1: " + magnitude1 + ", magnitude2: " + magnitude2);

        double[][] t3 = {
            {-magnitude1, 0, magnitude2, 0},
            {0, 1, 0, 0},
            {-magnitude2, 0, -magnitude1, 0},
            {0, 0, 0, 1}
        };

        double[][] t4 = {
            {1, 0, 0, 0},
            {0, magnitude1, magnitude2, 0},
            {0, -magnitude2, magnitude1, 0},
            {0, 0, 0, 1}
        };

        double[][] t5 = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };

        double[][] matrix1 = MatrixOperations.matrixMultiplication(t1, t2);
        double[][] matrix2 = MatrixOperations.matrixMultiplication(matrix1, t3);
        double[][] matrix3 = MatrixOperations.matrixMultiplication(matrix2, t4);

        return MatrixOperations.matrixMultiplication(matrix3, t5);
    }

    private double[][] getNMatrix() {
        double side = screenSize / 2;
        return new double[][] {
            {screenDistance / side, 0, 0, 0},
            {0, screenDistance / side, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
    }

    private List<Dataline> applyTransformation(double[][] matrix) {
        List<Dataline> transformedPoints = new ArrayList<>();
        for (Dataline line : datalines) {
            Dataline newLine = new Dataline(0, 0, 0, 0, 0, 0, line.getTag());
            newLine.setStart(MatrixOperations.matrixMultiplication(line.getStart(), matrix));
            newLine.setEnd(MatrixOperations.matrixMultiplication(line.getEnd(), matrix));
            transformedPoints.add(newLine);
        }
        return transformedPoints;
    }

    private void drawPoint(int x, int y) {
        Platform.runLater(() -> {
            gc.strokeLine(x, y, x, y);
        });
    }
}
