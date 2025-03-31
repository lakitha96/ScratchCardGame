package org.example.model.matrix;

/**
 * @author lakithaprabudh
 */
public class SymbolCell {
    private String symbol;
    private boolean isBonus;

    public SymbolCell(String symbol, boolean isBonus) {
        this.symbol = symbol;
        this.isBonus = isBonus;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean bonus) {
        isBonus = bonus;
    }
}
