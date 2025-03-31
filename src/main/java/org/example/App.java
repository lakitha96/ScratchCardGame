package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.loader.ConfigLoader;
import org.example.model.config.Config;
import org.example.model.matrix.SymbolCell;
import org.example.play.MatrixGenerator;
import org.example.play.RewardCalculator;
import org.example.play.WinEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Scratch Game Runner
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            Map<String, String> cliArgs = parseArguments(args);
            String configPath = cliArgs.get("config");
            int betAmount = Integer.parseInt(cliArgs.get("betting-amount"));

            if (configPath == null || betAmount <= 0) {
                log.error("Usage: --config <file> --betting-amount <amount>");
                return;
            }

            Config config = ConfigLoader.loadConfig(configPath);
            SymbolCell[][] matrix = generateMatrix(config);
            Map<String, List<String>> winningMap = WinEvaluator.evaluate(matrix, config.getWinCombinations());
            RewardCalculator.RewardResult result = RewardCalculator.calculateReward(
                    matrix, winningMap, config.getSymbols(), config.getWinCombinations(), betAmount
            );

            printMatrix(matrix);
            printResult(matrix, result, winningMap);

        } catch (Exception e) {
            log.error("An error occurred while running the game: {}", e.getMessage(), e);
        }
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                map.put(args[i].substring(2), args[i + 1]);
            }
        }
        return map;
    }

    private static SymbolCell[][] generateMatrix(Config config) {
        MatrixGenerator generator = new MatrixGenerator(config);
        return generator.generateMatrix();
    }

    private static void printMatrix(SymbolCell[][] matrix) {
        log.info("Matrix:");
        for (SymbolCell[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (SymbolCell cell : row) {
                sb.append(String.format("%-8s", cell.getSymbol()));
            }
            log.info(sb.toString());
        }
    }

    private static void printResult(SymbolCell[][] matrix, RewardCalculator.RewardResult result,
                                    Map<String, List<String>> winningMap) throws Exception {
        List<List<String>> displayMatrix = new ArrayList<>();
        for (SymbolCell[] row : matrix) {
            List<String> rowList = new ArrayList<>();
            for (SymbolCell cell : row) {
                rowList.add(cell.getSymbol());
            }
            displayMatrix.add(rowList);
        }

        Map<String, Object> finalOutput = new LinkedHashMap<>();
        finalOutput.put("matrix", displayMatrix);
        finalOutput.put("reward", (int) result.getReward());
        finalOutput.put("applied_winning_combinations", winningMap);
        finalOutput.put("applied_bonus_symbol", result.getAppliedBonusSymbol());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalOutput);
        log.info("Game Result (JSON):\n{}", json);
    }
}