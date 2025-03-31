package org.example;

import org.example.loader.ConfigLoader;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.play.MatrixGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author lakithaprabudh
 */
public class MatrixGeneratorTest {
    @Test
    public void testMatrixIsGeneratedWithCorrectSize() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");
        MatrixGenerator generator = new MatrixGenerator(config);
        SymbolCell[][] matrix = generator.generateMatrix();

        assertEquals(config.getRows(), matrix.length);
        assertEquals(config.getColumns(), matrix[0].length);
    }

    @Test
    public void testMatrixContainsNonNullSymbols() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");
        MatrixGenerator generator = new MatrixGenerator(config);
        SymbolCell[][] matrix = generator.generateMatrix();

        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                assertNotNull(cell.getSymbol());
            }
        }
    }
}
