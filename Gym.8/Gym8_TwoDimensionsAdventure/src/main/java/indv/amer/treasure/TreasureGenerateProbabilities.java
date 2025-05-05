package indv.amer.treasure;

import indv.amer.treasure.instance.*;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;


public enum TreasureGenerateProbabilities {
    SUPER_STAR("SuperStar", "0.1", "觸碰者獲得無敵狀態", SuperStar.class),
    POISON("Poison", "0.25", "觸碰者獲得中毒狀態", Poison.class),
    ACCELERATING_POTION("AcceleratingPotion", "0.2", "觸碰者獲得加速狀態", AcceleratingPotion.class),
    HEALING_POTION("HealingPotion", "0.15", "觸碰者獲得恢復狀態", HealingPotion.class),
    DEVIL_FRUIT("DevilFruit", "0.1", "觸碰者獲得混亂狀態", DevilFruit.class),
    KINGS_ROCK("KingsRock", "0.1", "觸碰者獲得蓄力狀態", KingsRock.class),
    DOKODEMO_DOOR("DokodemoDoor", "0.1", "觸碰者獲得瞬身狀態", DokodemoDoor.class);

    @Getter
    private final String TreasureName;
    @Getter
    private final BigDecimal probability;
    @Getter
    private final String effectDescription;
    private final Class<? extends Treasure> treasureClass;

    TreasureGenerateProbabilities(String _treasureName,
                                  String _probability,
                                  String _effectDescription,
                                  Class<? extends Treasure> _treasureClass) {
        this.TreasureName = _treasureName;
        this.probability = new BigDecimal(_probability);
        this.effectDescription = _effectDescription;
        this.treasureClass = _treasureClass;
    }


    public static boolean validateProbabilities() {
        BigDecimal sum = Arrays.stream(TreasureGenerateProbabilities.values())
                .map(TreasureGenerateProbabilities::getProbability)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 使用一個小的容許誤差來比較浮點數
        return sum.compareTo(new BigDecimal("1")) == 0;
    }

    public Treasure getInstance() {
        try {
            return this.treasureClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
