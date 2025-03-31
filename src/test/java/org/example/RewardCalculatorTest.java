package org.example;

import org.example.loader.ConfigLoader;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.play.RewardCalculator;
import org.example.play.WinEvaluator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        MatrixPrinterUtil.printMatrix(matrix);

        assertTrue(result.getReward() > 0, "Reward should be greater than zero");
        assertEquals("10x", result.getAppliedBonusSymbol(), "Bonus symbol mismatch");

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

        Map<String, List<String>> winningMap = WinEvaluator.evaluate(matrix, config.getWinCombinations());

        RewardCalculator.RewardResult result = RewardCalculator.calculateReward(
                matrix,
                winningMap,
                config.getSymbols(),
                config.getWinCombinations(),
                100
        );

        MatrixPrinterUtil.printMatrix(matrix);

        assertTrue(result.getReward() > 0, "Reward should be greater than zero");
        assertEquals("+500", result.getAppliedBonusSymbol(), "Bonus symbol mismatch");

        System.out.println("Winning Map: " + winningMap);
        System.out.println("Applied Bonus: " + result.getAppliedBonusSymbol());
        System.out.println("Final Reward: " + result.getReward());
    }
}