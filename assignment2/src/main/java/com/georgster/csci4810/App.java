package com.georgster.csci4810;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.GroupLayout.Group;

import com.georgster.csci4810.operator.DriverOptions;
import com.georgster.csci4810.operator.Transformer2D;
import com.georgster.csci4810.operator.TransformerDriver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

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
        Scanner input = new Scanner(System.in);
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
                System.out.println("Valid commands are: translate, scale, rotate, and exit");
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
        });

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