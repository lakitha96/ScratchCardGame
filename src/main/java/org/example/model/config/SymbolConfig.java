package org.example.model.config;

/**
 * @author lakithaprabudh
 */
public class SymbolConfig {
    public String type;
    public Double rewardMultiplier;
    public Integer extra;
    public String impact;

    public SymbolConfig() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(Double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
}
