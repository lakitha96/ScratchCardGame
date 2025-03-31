package org.example;

import org.example.loader.ConfigLoader;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.play.WinEvaluator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for WinEvaluator logic
 */
public class WinEvaluatorTest {

    @Test
    public void testSameSymbolCountMatchesWinCombo() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");

        SymbolCell[][] matrix = {
                { new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("A", false) },
                { new SymbolCell("B", false), new SymbolCell("C", false), new SymbolCell("D", false), new SymbolCell("E", false) },
                { new SymbolCell("F", false), new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("B", false) },
                { new SymbolCell("C", false), new SymbolCell("D", false), new SymbolCell("E", false), new SymbolCell("F", false) }
        };

        MatrixPrinterUtil.printMatrix(matrix);

        Map<String, List<String>> result = WinEvaluator.evaluate(matrix, config.getWinCombinations());

        System.out.println("Win Map: " + result);

        assertTrue(result.containsKey("A"), "Expected symbol 'A' to be in win map");
        assertTrue(result.get("A").contains("same_symbol_4_times"), "Expected 'same_symbol_4_times' to be in A's win list");
    }
}