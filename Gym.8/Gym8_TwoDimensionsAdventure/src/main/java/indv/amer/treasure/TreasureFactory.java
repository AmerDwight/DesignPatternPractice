package indv.amer.treasure;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;


@Slf4j
public class TreasureFactory {
    private final Random random;
    private final NavigableMap<BigDecimal, TreasureGenerateProbabilities> probabilityMap;

    public TreasureFactory() {
        // 驗證機率總和
        if (!TreasureGenerateProbabilities.validateProbabilities()) {
            BigDecimal sum = Arrays.stream(TreasureGenerateProbabilities.values())
                    .map(TreasureGenerateProbabilities::getProbability)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            throw new IllegalStateException("Treasure probabilities do not sum up to 1.0! Current sum: " + sum);
        }

        this.random = new Random();
        this.probabilityMap = new TreeMap<>();
        BigDecimal cumulativeProbability = new BigDecimal(0);

        for (TreasureGenerateProbabilities type : TreasureGenerateProbabilities.values()) {
            cumulativeProbability = cumulativeProbability.add(type.getProbability());
            this.probabilityMap.put(cumulativeProbability, type);
        }
    }

    /**
     * 根據定義的機率隨機生成一個寶物
     *
     * @return 一個隨機生成的 Treasure 物件，如果發生錯誤則可能返回 null 或拋出異常
     */
    public Treasure createRandomTreasure() {
        // 生成一個 0.0 (包含) 到 1.0 (不包含) 之間的隨機數
        double randomValue = random.nextDouble();

        /*
         * 使用 NavigableMap.ceilingEntry(key) 尋找 Map 中 Key 大於或等於 randomValue 的最小 Entry。
         * 例如：
         * Map: {0.1: STAR, 0.35: POISON, 0.55: ACCEL, ... , 1.0: DOOR}
         * randomValue = 0.05 -> ceilingEntry(0.05) 返回 {0.1: STAR} -> 選中 STAR
         * randomValue = 0.3  -> ceilingEntry(0.3)  返回 {0.35: POISON} -> 選中 POISON
         * randomValue = 0.95 -> ceilingEntry(0.95) 返回 {1.0: DOOR} -> 選中 DOOR
         */
        var entry = probabilityMap.ceilingEntry(new BigDecimal(randomValue));

        if (entry != null) {
            return new Treasure(entry.getValue());
        } else {
            log.error("Error: Could not determine treasure for random value: {}", randomValue);
            throw new RuntimeException("Failed to generate treasure. Check probability setup.");
        }
    }
}
