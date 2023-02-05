package com.georgster.csci4810;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Random rand = new Random();

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //Bresenham's line algorithm
    public void brz(int x1, int y1, int x2, int y2, GraphicsContext gc) {
        int dx = Math.abs(x2 - x1); // delta x
        int dy = Math.abs(y2 - y1); // delta y
        int sx = x1 < x2 ? 1 : -1; // sign x
        int sy = y1 < y2 ? 1 : -1; // sign y
        int d, x, y; // decision variable, x, y
    
        if (dx > dy) { // slope < 1
            d = dx / 2; // decision variable
            for (x = x1, y = y1; x != x2; x += sx) { // loop until x = x2
                gc.strokeLine(x, y, x + 1, y + 1); // draw line
                d -= dy; // subtract dy from decision variable
                if (d < 0) { // if decision variable < 0
                    d += dx; // add dx to decision variable
                    y += sy; // increment y by sign y
                }
            }
        } else { // slope >= 1
            d = dy / 2; // decision variable
            for (x = x1, y = y1; y != y2; y += sy) { // loop until y = y2
                gc.strokeLine(x, y, x + 1, y + 1); // draw line
                d -= dx; // subtract dx from decision variable
                if (d < 0) { // if decision variable < 0
                    d += dy; // add dy to decision variable
                    x += sx; // increment x by sign x
                }
            }
        }
        gc.strokeLine(x2, y2, x2 + 1, y2 + 1); // draw last line
    }

    public void brz2(GraphicsContext gc, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int e2;
    
        while (true) {
          gc.strokeLine(x0, y0, x0, y0);
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

    public static void Basic_alg(GraphicsContext gc, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
  
        if (dx == 0) {
            // Line is vertical, increment y coordinate
            int yStart = Math.min(y1, y2);
            int yEnd = Math.max(y1, y2);
            for (int y = yStart; y <= yEnd; y++) {
                gc.strokeLine(x1, y, x2, y);
            }
            return;
        }

        float m = (float)dy / (float)dx;
        float b = y1 - m * x1;

        int x, y, xend;
        if (dx < 0) {
            x = x2;
            y = y2;
            xend = x1;
        } else {
            x = x1;
            y = y1;
            xend = x2;
        }

        while (x != xend) {
            gc.strokeLine(x, y, x + 1, y + Math.round(m));
            x++;
            y = Math.round(m * x + b);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter how many lines to draw:");
        int n = input.nextInt(); // Number of lines to draw

        Group root = new Group();
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        BasicStopwatch stopwatch = new BasicStopwatch();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        stopwatch.start();

        for (int i = 0; i < n; i++) {
            //Create random coordinates for a line
            int x0 = rand.nextInt(500);
            int y0 = rand.nextInt(500);
            int x1 = rand.nextInt(500);
            int y1 = rand.nextInt(500);
            //coordinates for a perfectly vertical line
            /*int x0 = 250;
            int y0 = 0;
            int x1 = 250;
            int y1 = 500;*/
            //coordinates for a perfectly horizontal line
            /*int x0 = 0;
            int y0 = 250;
            int x1 = 500;
            int y1 = 250;*/
            //coordinates for a perfectly diagonal line with a positive slope
            /*int x0 = 0;
            int y0 = 0;
            int x1 = 500;
            int y1 = 500;*/
            //coordinates for a perfectly diagonal line with a negative slope
            /*int x0 = 0;
            int y0 = 500;
            int x1 = 500;
            int y1 = 0;*/
            Basic_alg(gc, x0, y0, x1, y1);
            //brz(x0, y0, x1, y1, gc);
            //brz2(gc, x0, y0, x1, y1);
        }

        stopwatch.stop();

        System.out.println("Time elapsed: " + stopwatch.getTimeElapsed() + " milliseconds");

        input.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}