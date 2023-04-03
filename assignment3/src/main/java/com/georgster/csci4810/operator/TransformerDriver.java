package com.georgster.csci4810.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A driver for the Transformer3D class.
 */
public class TransformerDriver {

    private Scanner scanner;
    private Transformer3D transformer;
    private Map<String, DriverOptions> options = new HashMap<>();
    private DriverOptions option;

    /**
     * Constructs a new TransformerDriver.
     *  
     * @param scanner The scanner to use for user input.
     * @param transformer The transformer to use for the driver.
     */
    public TransformerDriver(Scanner scanner, Transformer3D transformer) {
        this.scanner = scanner;
        this.transformer = transformer;
        options.put("translate", DriverOptions.TRANSLATE);
        options.put("rotate", DriverOptions.ROTATE);
        options.put("scale", DriverOptions.SCALE);
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
            case TRANSLATE:
                System.out.println("Enter the x, y, and z values to translate by:");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                double z = scanner.nextDouble();
                transformer.translate(x, y, z);
                break;
            case ROTATE:
                System.out.println("Enter the x, y, and z values to rotate by:");
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                z = scanner.nextDouble();
                transformer.rotate(x, y, z);
                break;
            case SCALE:
                System.out.println("Enter the x, y, and z values to scale by:");
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                z = scanner.nextDouble();
                transformer.scale(x, y, z);
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
