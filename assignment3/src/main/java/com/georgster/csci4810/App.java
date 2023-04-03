package com.georgster.csci4810;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

import com.georgster.csci4810.operator.DriverOptions;
import com.georgster.csci4810.operator.Transformer3D;
import com.georgster.csci4810.util.Dataline;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) {
        /*Scanner input = new Scanner(System.in);
        Group root = new Group();
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Transformer2D transformer = new Transformer2D(gc);
        TransformerDriver driver = new TransformerDriver(input, transformer);

        runDaemon(() -> {
            boolean isActive = true;
            while (isActive) {
                System.out.println("Valid commands are: input, output, display, translate, basicrotate, basicscale, scale, rotate, exit");
                driver.prompt("Enter a command:");
                DriverOptions option = driver.getOption();

                if (option == null) {
                    System.out.println("Invalid command.");
                } else if (option == DriverOptions.EXIT) {
                    isActive = false;
                } else {
                    driver.execute(option);
                }
            }
        });*/

        // Create a new PerspectiveCamera object and set its position and orientation to the desired values. In this case, the position is (6, 8, 7.5) and the viewing axis, Ze, is pointed directly at the origin of the WCS with the Xe-axis lying on the Z=7.5 plane.
        Group root = new Group();
        Canvas canvas = new Canvas(1024, 1024);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        final double[] viewpoint = {6, 8, 7.5};
        final double zPlane = 7.5;
        final double screenSize = 30;
        final double screenDistance = 60;

        Transformer3D transformer = new Transformer3D(gc, viewpoint, zPlane, screenSize, screenDistance);

        Dataline[] lines = {
            new Dataline(-1, 1, -1, 1, 1, -1), // AB
            new Dataline(1, 1, -1, 1, -1, -1), // BC
            new Dataline(1, -1, -1, -1, -1, -1), // CD
            new Dataline(-1, -1, -1, -1, 1, -1), // DA
            new Dataline(-1, 1, 1, 1, 1, 1), // EF
            new Dataline(1, 1, 1, 1, -1, 1), // FG
            new Dataline(1, -1, 1, -1, -1, 1), // GH
            new Dataline(-1, -1, 1, -1, 1, 1), // HE
            new Dataline(-1, 1, -1, -1, 1, 1), // AE
            new Dataline(1, 1, -1, 1, 1, 1), // BF
            new Dataline(1, -1, -1, 1, -1, 1), // CG
            new Dataline(-1, -1, -1, -1, -1, 1) // DH
        };
        
        for (Dataline line : lines) {
            transformer.addDataline(line);
        }

        transformer.transformPoints();

        // animate the box rotation
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void runDaemon(Runnable target) {
        Thread t = new Thread(target);
        t.setDaemon(true); //Daemon threads will not prevent the program from exiting
        t.start();
    }
}