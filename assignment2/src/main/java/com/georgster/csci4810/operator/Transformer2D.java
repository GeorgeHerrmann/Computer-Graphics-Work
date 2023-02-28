package com.georgster.csci4810.operator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.georgster.csci4810.util.Dataline;
import com.georgster.csci4810.util.MatrixOperations;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

/**
 * Responsible for transforming 2D lines
 */
public class Transformer2D {
    private static final String FILELOCATION = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "com", "georgster", "csci4810").toString();
    private List<Dataline> lines;
    private GraphicsContext gc;

    /**
     * Creates a 2D Line Transformer with no lines.
     * 
     * @param gc The GraphicsContext to draw the lines on
     */
    public Transformer2D(GraphicsContext gc) {
        this.lines = new ArrayList<>();
        this.gc = gc;
    }

    /**
     * Creates a 2D Line Transformer with the given lines.
     * 
     * @param lines The lines to transform
     * @param gc The GraphicsContext to draw the lines on
     */
    public Transformer2D(List<Dataline> lines, GraphicsContext gc) {
        this.lines = lines;
        this.gc = gc;
    }

    /**
     * Loads the lines from the given file.
     * 
     * @param filename The name of the file to load
     */
    public void inputLines(String filename) {
        try {
            String contents = Files.readString(Path.of(FILELOCATION, filename + ".txt"));
            String[] savedlines = contents.split("\n");
            for (String line : savedlines) {
                String[] points = line.split(" ");
                int x1 = Integer.parseInt(points[0]);
                int y1 = Integer.parseInt(points[1]);
                int x2 = Integer.parseInt(points[2]);
                int y2 = Integer.parseInt(points[3].replace("\n", "").trim());
                lines.add(new Dataline(x1, y1, x2, y2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs the lines to the given file.
     * 
     * @param filename The name of the file to output to
     */
    public void outputLines(String filename) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Dataline line : lines) {
                sb.append(line.getX1() + " " + line.getY1() + " " + line.getX2() + " " + line.getY2());
                if (lines.indexOf(line) != lines.size() - 1) {
                    sb.append("\n");
                }
            }
            Files.writeString(Path.of(FILELOCATION, filename + ".txt"), sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Translates the lines by the given x and y values.
     * 
     * @param x The x value to translate by
     * @param y The y value to translate by
     */
    public void basicTranslate(int x, int y) {
        int[][] matrix = new int[][] {
            { 1, 0, 0 },
            { 0, 1, 0 },
            { x, y, 1 }
        };
        applyTransformation(matrix);
    }

    /**
     * Scales the lines by the given x and y values.
     * 
     * @param x The x value to scale by
     * @param y The y value to scale by
     */
    public void basicScale(int x, int y) {
        int[][] matrix = new int[][] {
            { x, 0, 0 },
            { 0, y, 0 },
            { 0, 0, 1 }
        };
        applyTransformation(matrix);
    }

    /**
     * Rotates the lines by the given angle.
     * 
     * @param angle The angle to rotate by
     */
    public void basicRotate(double angle) {
        double[][] matrix = new double[][] {
            { Math.cos(angle), Math.sin(angle), 0 },
            { -Math.sin(angle), Math.cos(angle), 0 },
            { 0, 0, 1 }
        };
        applyTransformation(matrix);
    }
    
    /**
     * Applies a scale transformation to the lines.
     * 
     * @param x The x value to scale by
     * @param y The y value to scale by
     * @param cx The x value of the center of the scale
     * @param cy The y value of the center of the scale
     */
    public void scale(int x, int y, int cx, int cy) {
        int[][] translateMatrix1 = {
            { 1, 0, 0 },
            { 0, 1, 0 },
            { -cx, -cy, 1 }
        };

        int[][] scaleMatrix = {
            { x, 0, 0 },
            { 0, y, 0 },
            { 0, 0, 1 }
        };

        int[][] translateMatrix2 = {
            { 1, 0, 0 },
            { 0, 1, 0 },
            { cx, cy, 1 }
        };

        int[][] matrix1 = MatrixOperations.matrixMultiplication(translateMatrix1, scaleMatrix);
        int[][] matrix2 = MatrixOperations.matrixMultiplication(matrix1, translateMatrix2);
        applyTransformation(matrix2);
    }

    /**
     * Applies a rotation transformation to the lines.
     * 
     * @param angle The angle to rotate by
     * @param cx The x value of the center of the rotation
     * @param cy The y value of the center of the rotation
     */
    public void rotate(double angle, int cx, int cy) {
        double[][] translateMatrix1 = {
            { 1, 0, 0 },
            { 0, 1, 0 },
            { -cx, -cy, 1 }
        };

        double[][] rotateMatrix = {
            { Math.cos(angle), Math.sin(angle), 0 },
            { -Math.sin(angle), Math.cos(angle), 0 },
            { 0, 0, 1 }
        };

        double[][] translateMatrix2 = {
            { 1, 0, 0 },
            { 0, 1, 0 },
            { cx, cy, 1 }
        };

        double[][] matrix1 = MatrixOperations.matrixMultiplication(translateMatrix1, rotateMatrix);
        double[][] matrix2 = MatrixOperations.matrixMultiplication(matrix1, translateMatrix2);
        applyTransformation(matrix2);
    }

    /**
     * Transforms the lines with the given transformation matrix.
     * 
     * @param matrix The transformation matrix to apply
     */
    private void applyTransformation(double[][] matrix) {
        for (Dataline line : lines) {
            line.setStart(MatrixOperations.matrixMultiplication(line.getStart(), matrix));
            line.setEnd(MatrixOperations.matrixMultiplication(line.getEnd(), matrix));
        }
    }

    /**
     * Transforms the lines with the given transformation matrix.
     * 
     * @param matrix The transformation matrix to apply
     */
    private void applyTransformation(int[][] matrix) {
        for (Dataline line : lines) {
            line.setStart(MatrixOperations.matrixMultiplication(line.getStart(), matrix));
            line.setEnd(MatrixOperations.matrixMultiplication(line.getEnd(), matrix));
        }
    }

    /**
     * Draws the given Dataline on the GraphicsContext using the Bresenham algorithm.
     * 
     * @param line The line to draw
     */
    private void brz2(Dataline line) {
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

    private void drawPoint(int x, int y) {
        Platform.runLater(() -> {
            gc.strokeLine(x, y, x, y);
        });
    }

    /**
     * Displays this transformer's lines on the GraphicsContext.
     */
    public void displayPixels() {
        Platform.runLater(() -> gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()));
        for (Dataline line : lines) {
            brz2(line);
        }
    }

    public String toString() {
        String s = "";
        for (Dataline line : lines) {
            s += line.toString() + "\n";
        }
        return s;
    }
}
