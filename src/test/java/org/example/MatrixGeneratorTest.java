package org.example;

import org.example.loader.ConfigLoader;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.play.MatrixGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author lakithaprabudh
 */
public class MatrixGeneratorTest {

    @Test
    public void testMatrixIsGeneratedWithCorrectSize() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");
        MatrixGenerator generator = new MatrixGenerator(config);
        SymbolCell[][] matrix = generator.generateMatrix();

        MatrixPrinterUtil.printMatrix(matrix);

        assertEquals(config.getRows(), matrix.length, "Matrix row count mismatch");
        assertEquals(config.getColumns(), matrix[0].length, "Matrix column count mismatch");
    }

    @Test
    public void testMatrixContainsNonNullSymbols() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");
        MatrixGenerator generator = new MatrixGenerator(config);
        SymbolCell[][] matrix = generator.generateMatrix();

        MatrixPrinterUtil.printMatrix(matrix);

        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                assertNotNull(cell.getSymbol(), "Matrix cell has null symbol");
            }
        }
    }
}
