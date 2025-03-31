package org.example.model.config;

import java.util.List;

/**
 * @author lakithaprabudh
 */
public class ProbabilityConfig {
    private List<CellProbability> standardSymbols;
    private BonusProbability bonusSymbols;

    public ProbabilityConfig() {
    }

    public List<CellProbability> getStandardSymbols() {
        return standardSymbols;
    }

    public void setStandardSymbols(List<CellProbability> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

    public BonusProbability getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(BonusProbability bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }
}
