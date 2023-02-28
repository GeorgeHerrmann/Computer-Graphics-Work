package com.georgster.csci4810.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A driver for the Transformer2D class.
 */
public class TransformerDriver {

    private Scanner scanner;
    private Transformer2D transformer;
    private Map<String, DriverOptions> options = new HashMap<>();
    private DriverOptions option;

    /**
     * Constructs a new TransformerDriver.
     *  
     * @param scanner The scanner to use for user input.
     * @param transformer The transformer to use for the driver.
     */
    public TransformerDriver(Scanner scanner, Transformer2D transformer) {
        this.scanner = scanner;
        this.transformer = transformer;
        options.put("input", DriverOptions.INPUTLINES);
        options.put("output", DriverOptions.OUTPUTLINES);
        options.put("display", DriverOptions.DISPLAYPIXELS);
        options.put("translate", DriverOptions.TRANSLATE);
        options.put("basicrotate", DriverOptions.BASICROTATE);
        options.put("basicscale", DriverOptions.BASICSCALE);
        options.put("scale", DriverOptions.SCALE);
        options.put("rotate", DriverOptions.ROTATE);
        options.put("exit", DriverOptions.EXIT);
    }

    /**
     * Prompts the user for input and sets the option to the corresponding DriverOption.
     * 
     * @param message The message to display to the user.
     */
    public void prompt(String message) {
        System.out.println(message);
        String input = scanner.nextLine().toLowerCase();
        if (options.containsKey(input)) {
            option = options.get(input);
        } else {
            option = null;
        }
    }

    /**
     * Gets the option that was set by the prompt method.
     * 
     * @return The option that was set by the prompt method.
     */
    public DriverOptions getOption() {
        return option;
    }

    /**
     * Executes the option that was set by the prompt method.
     * 
     * @param option The option to execute.
     */
    public void execute(DriverOptions option) {
        switch (option) {
            case INPUTLINES:
                System.out.println("Enter the name of the file to read from:");
                String input = scanner.nextLine();
                transformer.inputLines(input);
                break;
            case OUTPUTLINES:
                System.out.println("Enter the name of the file to write to:");
                String output = scanner.nextLine();
                transformer.outputLines(output);
                break;
            case DISPLAYPIXELS:
                transformer.displayPixels();
                break;
            case TRANSLATE:
                System.out.println("Enter the x translation value:");
                int x = scanner.nextInt();
                System.out.println("Enter the y translation value:");
                int y = scanner.nextInt();
                transformer.basicTranslate(x, y);
                break;
            case BASICROTATE:
                System.out.println("Enter the rotation angle:");
                int angle = scanner.nextInt();
                transformer.basicRotate(angle);
                break;
            case BASICSCALE:
                System.out.println("Enter the x scale value:");
                int xScale = scanner.nextInt();
                System.out.println("Enter the y scale value:");
                int yScale = scanner.nextInt();
                transformer.basicScale(xScale, yScale);
                break;
            case SCALE:
                System.out.println("Enter the x scale value:");
                int xScale2 = scanner.nextInt();
                System.out.println("Enter the y scale value:");
                int yScale2 = scanner.nextInt();
                System.out.println("Enter the x scale center:");
                int xCenter = scanner.nextInt();
                System.out.println("Enter the y scale center:");
                int yCenter = scanner.nextInt();
                transformer.scale(xScale2, yScale2, xCenter, yCenter);
                break;
            case ROTATE:
                System.out.println("Enter the rotation angle:");
                int angle2 = scanner.nextInt();
                System.out.println("Enter the x rotation center:");
                int xCenter2 = scanner.nextInt();
                System.out.println("Enter the y rotation center:");
                int yCenter2 = scanner.nextInt();
                transformer.rotate(angle2, xCenter2, yCenter2);
                break;
            case EXIT:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}
