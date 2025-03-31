package org.example.model.config;

import java.util.Map;

/**
 * @author lakithaprabudh
 */
public class CellProbability {
    private int row;
    private int column;
    private Map<String, Integer> symbols;

    public CellProbability() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }
}
