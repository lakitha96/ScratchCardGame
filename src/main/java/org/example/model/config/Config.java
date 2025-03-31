package org.example.model.config;

import java.util.Map;

/**
 * @author lakithaprabudh
 */
public class Config {
    public int columns;
    public int rows;
    public Map<String, SymbolConfig> symbols;
    public ProbabilityConfig probabilities;
    public Map<String, WinningCombination> winCombinations;

    public Config() {
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, SymbolConfig> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, SymbolConfig> symbols) {
        this.symbols = symbols;
    }

    public ProbabilityConfig getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(ProbabilityConfig probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinningCombination> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinningCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }
}
