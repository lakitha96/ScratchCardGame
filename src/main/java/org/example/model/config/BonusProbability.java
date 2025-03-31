package org.example.model.config;

import java.util.Map;

/**
 * @author lakithaprabudh
 */
public class BonusProbability {
    private Map<String, Integer> symbols;

    public BonusProbability() {
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }
}
