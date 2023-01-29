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

    public static void basicAlg(GraphicsContext gc, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0); // delta x
        int dy = Math.abs(y1 - y0); // delta y
        int sx = x0 < x1 ? 1 : -1; // sign x
        int sy = y0 < y1 ? 1 : -1; // sign y
        int err = dx - dy; // error

        while (true) { // loop until x = x2 and y = y2
            gc.strokeLine(x0, y0, x0 + 1, y0 + 1); // draw line
            if (x0 == x1 && y0 == y1) {
                break;
            }
            int e2 = 2 * err; // error * 2 represents the error at the next pixel
            if (e2 > -dy) { // if error * 2 > -dy
                err -= dy; // subtract dy from error
                x0 += sx; // increment x by sign x
            }
            if (e2 < dx) { // if error * 2 < dx
                err += dx; // add dx to error
                y0 += sy; // increment y by sign y
            }
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

        for (int i = 0; i < n; i++) {
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
            basicAlg(gc, x0, y0, x1, y1);
            //brz(x0, y0, x1, y1, root);
        }

        stopwatch.stop();

        System.out.println("Time elapsed: " + stopwatch.getTimeElapsed() + " milliseconds");

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        input.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}