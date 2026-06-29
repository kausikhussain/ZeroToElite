package dsa;

/**
 * 08_DSA_Implementations - Matrix Operations
 * This class provides methods to perform linear algebra operations using 2D arrays:
 * 1. Matrix Addition (Matrices must have identical dimensions)
 * 2. Matrix Multiplication (Number of columns in Matrix A must equal number of rows in Matrix B)
 */
public class MatrixOperations {

    public static void main(String[] args) {
        int[][] matA = {
            {1, 2},
            {3, 4}
        };

        int[][] matB = {
            {5, 6},
            {7, 8}
        };

        System.out.println("=== Matrix A ===");
        printMatrix(matA);
        System.out.println("=== Matrix B ===");
        printMatrix(matB);

        System.out.println("\n=== Matrix Addition ===");
        int[][] addResult = addMatrices(matA, matB);
        if (addResult != null) {
            printMatrix(addResult);
        }

        System.out.println("\n=== Matrix Multiplication ===");
        int[][] mulResult = multiplyMatrices(matA, matB);
        if (mulResult != null) {
            printMatrix(mulResult);
        }
    }

    /**
     * Performs addition of two 2D matrices.
     * Time Complexity: O(rows * cols), Space Complexity: O(rows * cols) for the result.
     */
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;

        // Verify dimensions match
        if (b.length != rows || b[0].length != cols) {
            System.out.println("Error: Matrix dimensions do not match. Addition impossible.");
            return null;
        }

        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    /**
     * Performs matrix multiplication: Result[i][j] = sum(A[i][k] * B[k][j])
     * Time Complexity: O(r1 * c2 * c1), Space Complexity: O(r1 * c2) for the result.
     */
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int r1 = a.length;
        int c1 = a[0].length;
        int r2 = b.length;
        int c2 = b[0].length;

        // Verify dimension compatibility
        if (c1 != r2) {
            System.out.println("Error: Matrix A columns (" + c1 + ") must equal Matrix B rows (" + r2 + ").");
            return null;
        }

        int[][] result = new int[r1][c2];

        // Multiplication logic: O(n^3) complexity
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Helper utility to print matrices to console.
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
