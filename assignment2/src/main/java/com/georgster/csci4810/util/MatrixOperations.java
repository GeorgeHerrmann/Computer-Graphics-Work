package com.georgster.csci4810.util;

public class MatrixOperations {
    private MatrixOperations() {
        // Used to assist App.java for Matrix Operations, does not need to be instantiated
        throw new UnsupportedOperationException("Utility class.");
    }
    
    public static double[][] matrixMultiplication(double[][] matrix1, double[][] matrix2) {
        // Determine the dimensions of the matrices
        int m1Rows = matrix1.length;
        int m1Cols = matrix1[0].length;
        int m2Rows = matrix2.length;
        int m2Cols = matrix2[0].length;
    
        // Check if the matrices are compatible for multiplication
        if (m1Cols != m2Rows) {
            throw new IllegalArgumentException("Matrices are not compatible for multiplication.");
        }
    
        // Create a new matrix to hold the result of the multiplication
        double[][] resultMatrix = new double[m1Rows][m2Cols];
    
        // Perform the matrix multiplication operation
        for (int i = 0; i < m1Rows; i++) {
            for (int j = 0; j < m2Cols; j++) {
                double sum = 0.0;
                for (int k = 0; k < m1Cols; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
    
        // Return the result matrix
        return resultMatrix;
    }

    public static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
        // Determine the dimensions of the matrices
        int m1Rows = matrix1.length;
        int m1Cols = matrix1[0].length;
        int m2Rows = matrix2.length;
        int m2Cols = matrix2[0].length;
    
        // Check if the matrices are compatible for multiplication
        if (m1Cols != m2Rows) {
            throw new IllegalArgumentException("Matrices are not compatible for multiplication.");
        }
    
        // Create a new matrix to hold the result of the multiplication
        int[][] resultMatrix = new int[m1Rows][m2Cols];
    
        // Perform the matrix multiplication operation
        for (int i = 0; i < m1Rows; i++) {
            for (int j = 0; j < m2Cols; j++) {
                int sum = 0;
                for (int k = 0; k < m1Cols; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
    
        // Return the result matrix
        return resultMatrix;
    }

    /**
     * Multiplies a 1xN matrix by an NxM matrix.
     * 
     * @param matrix1 1xN matrix
     * @param matrix2 NxM matrix
     * @return 1xM matrix
     */
    public static int[] matrixMultiplication(int[] matrix1, int[][] matrix2) {
        int numRows = matrix2.length;
        int numCols = matrix2[0].length;
        int[] result = new int[numCols];
        
        if (matrix1.length != numRows) {
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }
        
        for (int i = 0; i < numCols; i++) {
            int sum = 0;
            for (int j = 0; j < numRows; j++) {
                sum += matrix1[j] * matrix2[j][i];
            }
            result[i] = sum;
        }
        
        return result;
    }

    /**
     * Multiplies a 1xN matrix by an NxM matrix.
     * 
     * @param matrix1 1xN matrix
     * @param matrix2 NxM matrix
     * @return 1xM matrix
     */
    public static int[] matrixMultiplication(int[] matrix1, double[][] matrix2) {
        int numRows = matrix2.length;
        int numCols = matrix2[0].length;
        int[] result = new int[numCols];
        
        if (matrix1.length != numRows) {
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }
        
        for (int i = 0; i < numCols; i++) {
            int sum = 0;
            for (int j = 0; j < numRows; j++) {
                sum += Math.round((matrix1[j] * matrix2[j][i]));
            }
            result[i] = sum;
        }
        
        return result;
    }
}
