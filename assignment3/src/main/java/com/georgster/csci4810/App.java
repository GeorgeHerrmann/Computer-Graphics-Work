package com.georgster.csci4810;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

import com.georgster.csci4810.operator.DriverOptions;
import com.georgster.csci4810.operator.Transformer3D;
import com.georgster.csci4810.operator.Transformer3DDriver;
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

        Group root = new Group();
        Canvas canvas = new Canvas(1024, 1024);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        final double[] viewpoint = {6, 8, 7.5};
        final double zPlane = 7.5;
        final double screenSize = 30;
        final double screenDistance = 60;

        runDaemon(() -> {
            Transformer3D transformer = new Transformer3D(gc, viewpoint, zPlane, screenSize, screenDistance);
            Transformer3DDriver driver = new Transformer3DDriver(new Scanner(System.in), transformer);

            Dataline[] lines = {
                new Dataline(-1, 1, -1, 1, 1, -1, "AB"), // AB
                new Dataline(1, 1, -1, 1, -1, -1, "BC"), // BC
                new Dataline(1, -1, -1, -1, -1, -1, "CD"), // CD
                new Dataline(-1, -1, -1, -1, 1, -1, "DA"), // DA
                new Dataline(-1, 1, 1, 1, 1, 1, "EF"), // EF
                new Dataline(1, 1, 1, 1, -1, 1, "FG"), // FG
                new Dataline(1, -1, 1, -1, -1, 1, "GH"), // GH
                new Dataline(-1, -1, 1, -1, 1, 1, "HE"), // HE
                new Dataline(-1, 1, -1, -1, 1, 1, "AE"), // AE
                new Dataline(1, 1, -1, 1, 1, 1, "BF"), // BF
                new Dataline(1, -1, -1, 1, -1, 1, "CG"), // CG
                new Dataline(-1, -1, -1, -1, -1, 1, "DH") // DH
            };
            

            for (Dataline line : lines) {
                transformer.addDataline(line);
            }
            transformer.transformPoints();
            boolean isActive = true;
            while (isActive) {
                System.out.println("Valid commands are: translate, rotate, scale, and exit");
                driver.prompt("Enter a command:");
                DriverOptions option = driver.getOption();

                if (option == null) {
                    System.out.println("Invalid command.");
                } else if (option == DriverOptions.EXIT) {
                    isActive = false;
                } else {
                    driver.execute(option);
                    transformer.transformPoints();
                }
            }
        });

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