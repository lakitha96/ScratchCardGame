package org.example.util;

import java.util.Map;
import java.util.Random;

/**
 * @author lakithaprabudh
 */
public class RandomPicker {
    public static String pickByWeight(Map<String, Integer> weightedMap) {
        int totalWeight = weightedMap.values().stream().mapToInt(i -> i).sum();
        int random = new Random().nextInt(totalWeight);
        int cumulative = 0;

        //loop for weights and find random symbol
        for (Map.Entry<String, Integer> entry : weightedMap.entrySet()) {
            cumulative += entry.getValue();
            if (random < cumulative) {
                return entry.getKey();
            }
        }
        return null;
    }
}
