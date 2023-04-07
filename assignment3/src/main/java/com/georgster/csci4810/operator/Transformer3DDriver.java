package com.georgster.csci4810.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A class to handle user input and execute the corresponding option for the given Transformer3D.
 */
public class Transformer3DDriver {
    private Scanner scanner;
    private Transformer3D transformer;
    private Map<String, DriverOptions> options = new HashMap<>();
    private DriverOptions option;

    /**
     * Constructs a new Transformer3DDriver with the given Scanner and Transformer3D.
     * 
     * @param scanner The Scanner to use for user input.
     * @param transformer The Transformer3D to use for executing options.
     */
    public Transformer3DDriver(Scanner scanner, Transformer3D transformer) {
        this.scanner = scanner;
        this.transformer = transformer;
        options.put("translate", DriverOptions.TRANSLATE);
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
     * Executes the given option.
     * 
     * @param option The option to execute.
     */
    public void execute(DriverOptions option) {
        switch (option) {
            case TRANSLATE:
                System.out.println("Enter the x value to translate by: ");
                double x = scanner.nextDouble();
                System.out.println("Enter the y value to translate by: ");
                double y = scanner.nextDouble();
                System.out.println("Enter the z value to translate by: ");
                double z = scanner.nextDouble();
                transformer.applyTranslation(x, y, z);
                break;
            case SCALE:
                System.out.println("Enter the x value to scale by: ");
                x = scanner.nextDouble();
                System.out.println("Enter the y value to scale by: ");
                y = scanner.nextDouble();
                System.out.println("Enter the z value to scale by: ");
                z = scanner.nextDouble();
                transformer.applyScale(x, y, z);
                break;
            case ROTATE:
                System.out.println("Enter the axis to rotate around (x, y, or z): ");
                try {
                    String axis = scanner.nextLine();
                    System.out.println("Enter the angle to rotate by: ");
                    double angle = scanner.nextDouble();
                    transformer.applyRotation(axis, angle);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
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
