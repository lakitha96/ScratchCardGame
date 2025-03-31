package org.example;

import org.example.loader.ConfigLoader;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.play.RewardCalculator;
import org.example.play.WinEvaluator;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author lakithaprabudh
 */
public class RewardCalculatorTest {
    @Test
    public void testRewardCalculationWithWinAndBonus() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");

        SymbolCell[][] matrix = {
                {new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("10x", true)},
                {new SymbolCell("B", false), new SymbolCell("C", false), new SymbolCell("D", false), new SymbolCell("E", false)},
                {new SymbolCell("F", false), new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("B", false)},
                {new SymbolCell("C", false), new SymbolCell("D", false), new SymbolCell("E", false), new SymbolCell("F", false)}
        };

        Map<String, List<String>> winningMap = new HashMap<>();
        winningMap.put("A", Arrays.asList("same_symbol_3_times", "same_symbol_4_times", "same_symbol_5_times"));

        RewardCalculator.RewardResult result = RewardCalculator.calculateReward(
                matrix,
                winningMap,
                config.getSymbols(),
                config.getWinCombinations(),
                100
        );

        assertTrue(result.getReward() > 0);
        assertEquals("10x", result.getAppliedBonusSymbol());

        System.out.println("Test: Reward Calculation With Win And Bonus");
        System.out.println("Matrix:");
        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                System.out.print(cell.getSymbol() + "\t");
            }
            System.out.println();
        }
        System.out.println("Winning Map: " + winningMap);
        System.out.println("Applied Bonus: " + result.getAppliedBonusSymbol());
        System.out.println("Final Reward: " + result.getReward());
    }

    @Test
    public void testConfigBasedMatrixReward() throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");

        SymbolCell[][] matrix = {
                {new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("A", false), new SymbolCell("C", false)},
                {new SymbolCell("F", false), new SymbolCell("B", false), new SymbolCell("C", false), new SymbolCell("C", false)},
                {new SymbolCell("F", false), new SymbolCell("D", false), new SymbolCell("C", false), new SymbolCell("B", false)},
                {new SymbolCell("+500", true), new SymbolCell("E", false), new SymbolCell("F", false), new SymbolCell("F", false)}
        };

        // Evaluate wins
        Map<String, List<String>> winningMap = WinEvaluator.evaluate(matrix, config.getWinCombinations());

        // Calculate reward
        RewardCalculator.RewardResult result = RewardCalculator.calculateReward(
                matrix,
                winningMap,
                config.getSymbols(),
                config.getWinCombinations(),
                100
        );

        assertTrue(result.getReward() > 0);
        assertEquals("+500", result.getAppliedBonusSymbol());

        System.out.println("Reward: " + result.getReward());
        System.out.println("Win map: " + winningMap);
    }
}
