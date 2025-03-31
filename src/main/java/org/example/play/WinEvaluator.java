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
    public static Map<String, List<String>> evaluate(SymbolCell[][] matrix,
                                                     Map<String, WinningCombination> winCombinations) {

        Map<String, List<String>> result = new HashMap<>();

        // STEP 1: Count all standard symbols (ignore bonus symbols)
        Map<String, Integer> symbolCount = new HashMap<>();
        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                if (!cell.isBonus()) {
                    //count and update how many times each standard symbol appears
                    symbolCount.put(cell.getSymbol(),
                            symbolCount.getOrDefault(cell.getSymbol(), 0) + 1);
                }
            }
        }

        // STEP 2: Check 'same_symbols' combinations
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

        // STEP 3: Check linear pattern wins (row/col/diag) using coveredAreas
        for (Map.Entry<String, WinningCombination> entry : winCombinations.entrySet()) {
            WinningCombination combo = entry.getValue();
            if ("linear_symbols".equals(combo.getWhen())) {
                List<List<String>> areas = combo.getCoveredAreas();

                for (List<String> area : areas) {
                    String candidateSymbol = null;
                    boolean matched = true;

                    for (String cellRef : area) {
                        String[] parts = cellRef.split(":");
                        int row = Integer.parseInt(parts[0]);
                        int col = Integer.parseInt(parts[1]);

                        //if row is out of matrix skip
                        if (row >= matrix.length || col >= matrix[0].length) {
                            matched = false;
                            break;
                        }

                        SymbolCell cell = matrix[row][col];

                        //if cell is bonus skip
                        if (cell.isBonus()) {
                            matched = false;
                            break;
                        }

                        //all spot has same symbol decide as win
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

        return result;
    }
}
