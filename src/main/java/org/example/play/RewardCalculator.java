package org.example.play;

import org.example.model.config.SymbolConfig;
import org.example.model.config.WinningCombination;
import org.example.model.matrix.SymbolCell;

import java.util.List;
import java.util.Map;

/**
 * @author lakithaprabudh
 */
public class RewardCalculator {
    public static class RewardResult {
        private double reward;
        private String appliedBonusSymbol;

        public RewardResult(double reward, String appliedBonusSymbol) {
            this.reward = reward;
            this.appliedBonusSymbol = appliedBonusSymbol;
        }

        public double getReward() {
            return reward;
        }

        public String getAppliedBonusSymbol() {
            return appliedBonusSymbol;
        }
    }

    public static RewardResult calculateReward(SymbolCell[][] matrix,
                                               Map<String, List<String>> winningMap,
                                               Map<String, SymbolConfig> symbolConfigMap,
                                               Map<String, WinningCombination> winCombinations,
                                               int betAmount) {

        double totalReward = 0.0;

        for (Map.Entry<String, List<String>> entry : winningMap.entrySet()) {
            String symbol = entry.getKey();
            List<String> matchedWinCombos = entry.getValue();

            SymbolConfig symbolConfig = symbolConfigMap.get(symbol);
            if (symbolConfig == null || !"standard".equals(symbolConfig.getType())) continue;

            double symbolReward = betAmount * symbolConfig.getRewardMultiplier();

            for (String comboKey : matchedWinCombos) {
                WinningCombination combo = winCombinations.get(comboKey);
                if (combo != null) {
                    symbolReward *= combo.getRewardMultiplier();
                }
            }

            totalReward += symbolReward;
        }

        // Check for any bonus symbol in the matrix
        String bonusSymbol = findFirstBonusSymbol(matrix);
        if (bonusSymbol != null && totalReward > 0) {
            SymbolConfig bonusConfig = symbolConfigMap.get(bonusSymbol);
            if (bonusConfig != null && "bonus".equals(bonusConfig.getType())) {
                String impact = bonusConfig.getImpact();
                if ("multiply_reward".equals(impact)) {
                    totalReward *= bonusConfig.getRewardMultiplier();
                } else if ("extra_bonus".equals(impact)) {
                    totalReward += bonusConfig.getExtra();
                }
                // MISS does nothing
            }
        } else {
            bonusSymbol = null; // No bonus or reward is zero
        }

        return new RewardResult(totalReward, bonusSymbol);
    }

    private static String findFirstBonusSymbol(SymbolCell[][] matrix) {
        for (SymbolCell[] row : matrix) {
            for (SymbolCell cell : row) {
                if (cell.isBonus()) {
                    return cell.getSymbol();
                }
            }
        }
        return null;
    }
}
