package org.example.model.config;

import java.util.List;

/**
 * @author lakithaprabudh
 */
public class WinningCombination {
    private double rewardMultiplier;
    private String when; // "same_symbols" or "linear_symbols"
    private int count;   // Only used for "same_symbols"
    private String group;
    private List<List<String>> coveredAreas; // Used for linear patterns

    public WinningCombination() {
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<List<String>> getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(List<List<String>> coveredAreas) {
        this.coveredAreas = coveredAreas;
    }
}
