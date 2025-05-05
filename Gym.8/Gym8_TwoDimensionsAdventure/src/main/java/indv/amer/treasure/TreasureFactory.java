package indv.amer.treasure;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;


@Slf4j
public class TreasureFactory {
    private final Random random = new Random();
    private final NavigableMap<BigDecimal, TreasureGenerateProbabilities> probabilityMap;

    public TreasureFactory() {
        // 驗證機率總和
        if (!TreasureGenerateProbabilities.validateProbabilities()) {
            BigDecimal sum = Arrays.stream(TreasureGenerateProbabilities.values())
                    .map(TreasureGenerateProbabilities::getProbability)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            throw new IllegalStateException("Treasure probabilities do not sum up to 1.0! Current sum: " + sum);
        }

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
        try {
            // 生成 0 到 1 之間的隨機數
            BigDecimal randomValue = new BigDecimal(this.random.nextDouble());
            log.debug("生成隨機數: {}", randomValue);

            // 查找對應的寶物類型
            Map.Entry<BigDecimal, TreasureGenerateProbabilities> entry =
                    this.probabilityMap.ceilingEntry(randomValue);

            if (entry == null) {
                log.error("無法根據隨機值 {} 找到對應的寶物類型", randomValue);
                throw new IllegalStateException("Could not find treasure type for random value: " + randomValue);
            }

            TreasureGenerateProbabilities selectedType = entry.getValue();
            log.info("生成寶物類型: {}, 描述: {}", selectedType.getTreasureName(), selectedType.getEffectDescription());

            // 使用 getInstance 方法創建寶物實例
            return selectedType.getInstance();

        } catch (Exception e) {
            log.error("創建隨機寶物時發生錯誤", e);
            throw new RuntimeException("Error creating random treasure", e);
        }
    }
}
