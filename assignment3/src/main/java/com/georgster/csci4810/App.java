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
import com.georgster.csci4810.util.DatalineShapes;

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
        int n = 1024;
        Canvas canvas = new Canvas(n, n);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        final double[] viewpoint = {6, 8, 7.5};
        final double zPlane = 7.5;
        final double screenSize = 30;
        final double screenDistance = 60;

        runDaemon(() -> {
            Transformer3D transformer = new Transformer3D(gc, viewpoint, zPlane, screenSize, screenDistance, n);
            Transformer3DDriver driver = new Transformer3DDriver(new Scanner(System.in), transformer);
            

            for (Dataline line : DatalineShapes.getPentagonalPyramid()) {
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