package org.example;

import org.example.model.matrix.SymbolCell;

/**
 * @author lakithaprabudh
 */
public class MatrixPrinterUtil {
    public static void printMatrix(SymbolCell[][] matrix) {
        System.out.println("\nGenerated Matrix:");
        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                System.out.printf("%-8s", cell.getSymbol());
            }
            System.out.println();
        }
    }
}
