package org.example.play;

import org.example.model.config.WinningCombination;
import org.example.model.matrix.SymbolCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lakithaprabudh
 */
public class WinEvaluator {

    /**
     * Evaluates the matrix for winning combinations based on the configuration.
     *
     * @param matrix the 2D matrix
     * @param winCombinations the set of win combination rules
     * @return result of winner data map with key as the symbol and the value as a list of win combination keys that apply
     */
    public static Map<String, List<String>> evaluate(SymbolCell[][] matrix,
                                                     Map<String, WinningCombination> winCombinations) {

        Map<String, List<String>> result = new HashMap<>();

        Map<String, Integer> symbolCount = countStandardSymbols(matrix);
        applySameSymbolWins(symbolCount, winCombinations, result);
        applyLinearPatternWins(matrix, winCombinations, result);

        return result;
    }

    // STEP 1: Count all standard symbols (ignore bonus symbols)
    private static Map<String, Integer> countStandardSymbols(SymbolCell[][] matrix) {
        Map<String, Integer> symbolCount = new HashMap<>();
        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                if (!cell.isBonus()) {
                    symbolCount.put(cell.getSymbol(),
                            symbolCount.getOrDefault(cell.getSymbol(), 0) + 1);
                }
            }
        }
        return symbolCount;
    }

    // STEP 2: Apply 'same_symbols' winning combinations
    private static void applySameSymbolWins(Map<String, Integer> symbolCount,
                                            Map<String, WinningCombination> winCombinations,
                                            Map<String, List<String>> result) {
        for (Map.Entry<String, WinningCombination> entry : winCombinations.entrySet()) {
            WinningCombination combo = entry.getValue();
            if ("same_symbols".equals(combo.getWhen())) {
                for (Map.Entry<String, Integer> symbolEntry : symbolCount.entrySet()) {
                    String symbol = symbolEntry.getKey();
                    int count = symbolEntry.getValue();
                    if (count >= combo.getCount()) {
                        result.computeIfAbsent(symbol, k -> new ArrayList<>()).add(entry.getKey());
                    }
                }
            }
        }
    }

    // STEP 3: Apply linear pattern wins (horizontal, vertical, diagonal)
    private static void applyLinearPatternWins(SymbolCell[][] matrix,
                                               Map<String, WinningCombination> winCombinations,
                                               Map<String, List<String>> result) {
        for (Map.Entry<String, WinningCombination> entry : winCombinations.entrySet()) {
            WinningCombination combo = entry.getValue();

            if (!"linear_symbols".equals(combo.getWhen())) continue;

            List<List<String>> areas = combo.getCoveredAreas();
            for (List<String> area : areas) {
                String candidateSymbol = null;
                boolean matched = true;

                for (String cellRef : area) {
                    String[] parts = cellRef.split(":");
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);

                    if (row >= matrix.length || col >= matrix[0].length) {
                        matched = false;
                        break;
                    }

                    SymbolCell cell = matrix[row][col];
                    if (cell.isBonus()) {
                        matched = false;
                        break;
                    }

                    if (candidateSymbol == null) {
                        candidateSymbol = cell.getSymbol();
                    } else if (!candidateSymbol.equals(cell.getSymbol())) {
                        matched = false;
                        break;
                    }
                }

                if (matched && candidateSymbol != null) {
                    result.computeIfAbsent(candidateSymbol, k -> new ArrayList<>()).add(entry.getKey());
                }
            }
        }
    }
}
