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

    public void brz(int x1, int y1, int x2, int y2, Group group) {
        int dx = Math.abs(x2 - x1); // delta x
        int dy = Math.abs(y2 - y1); // delta y
        int sx = x1 < x2 ? 1 : -1; // sign x
        int sy = y1 < y2 ? 1 : -1; // sign y
        int d, x, y; // decision variable, x, y
    
        if (dx > dy) { // slope < 1
            d = dx / 2; // decision variable
            for (x = x1, y = y1; x != x2; x += sx) { // loop until x = x2
                group.getChildren().add(new Line(x, y, x, y)); // draw line
                d -= dy; // subtract dy from decision variable
                if (d < 0) { // if decision variable < 0
                    d += dx; // add dx to decision variable
                    y += sy; // increment y by sign y
                }
            }
        } else { // slope >= 1
            d = dy / 2; // decision variable
            for (x = x1, y = y1; y != y2; y += sy) { // loop until y = y2
                group.getChildren().add(new Line(x, y, x, y)); // draw line
                d -= dx; // subtract dx from decision variable
                if (d < 0) { // if decision variable < 0
                    d += dy; // add dy to decision variable
                    x += sx; // increment x by sign x
                }
            }
        }
        group.getChildren().add(new Line(x2, y2, x2, y2)); // draw last line
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
        stopwatch.start();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        for (int i = 0; i < n; i++) {
            //Create random coordinates for a line
            int x0 = rand.nextInt(500);
            int y0 = rand.nextInt(500);
            int x1 = rand.nextInt(500);
            int y1 = rand.nextInt(500);
            //Create coordinates for a perfectly vertical line
            /*int x0 = 250;
            int y0 = 0;
            int x1 = 250;
            int y1 = 500;*/
            //Create coordinates for a perfectly horizontal line
            /*int x0 = 0;
            int y0 = 250;
            int x1 = 500;
            int y1 = 250;*/
            //Create coordinates for a perfectly diagonal line
            /*int x0 = 0;
            int y0 = 0;
            int x1 = 500;
            int y1 = 500;*/
            Basic_alg(gc, x0, y0, x1, y1);
            //brz(x0, y0, x1, y1, root);
        }

        stopwatch.stop();

        System.out.println("Time elapsed: " + stopwatch.getTimeElapsed() + " milliseconds");

        input.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}