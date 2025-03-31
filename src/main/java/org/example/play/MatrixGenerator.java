package org.example.play;

import org.example.model.config.CellProbability;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.util.RandomPicker;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author lakithaprabudh
 */
public class MatrixGenerator {
    private final Config config;
    private final int rows;
    private final int columns;

    public MatrixGenerator(Config config) {
        this.config = config;
        this.rows = config.getRows();
        this.columns = config.getColumns();
    }

    public SymbolCell[][] generateMatrix() {
        SymbolCell[][] matrix = new SymbolCell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                String symbol = getRandomStandardSymbol(row, col);
                matrix[row][col] = new SymbolCell(symbol, false);
            }
        }


        placeRandomBonusSymbol(matrix);
        return matrix;
    }

    private String getRandomStandardSymbol(int row, int col) {
        List<CellProbability> cellProbabilities = config.getProbabilities().getStandardSymbols();

        // Try to find cell-specific probabilities
        for (CellProbability cp : cellProbabilities) {
            if (cp.getRow() == row && cp.getColumn() == col) {
                return RandomPicker.pickByWeight(cp.getSymbols());
            }
        }

        // if randomPicker couldn't find one will replace default one
        return RandomPicker.pickByWeight(cellProbabilities.get(0).getSymbols());
    }


    private void placeRandomBonusSymbol(SymbolCell[][] matrix) {
        Map<String, Integer> bonusSymbols = config.getProbabilities().getBonusSymbols().getSymbols();
        String bonusSymbol = RandomPicker.pickByWeight(bonusSymbols);

        Random random = new Random();
        int row = random.nextInt(rows);
        int col = random.nextInt(columns);

        matrix[row][col] = new SymbolCell(bonusSymbol, true);
    }
}
