package indv.amer.pattern;

import indv.amer.Big2CardBasicComparator;
import indv.amer.Validated;
import indv.amer.poker.PokerCard;
import indv.amer.poker.PokerRank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class FullHousePattern extends CardPatternTemplate {
    public FullHousePattern(CardPatternTemplate nextTemplate) {
        super(nextTemplate);
    }

    @Override
    protected boolean patternVerify(List<PokerCard> cards) {
        if (cards == null || cards.size() != 5) {
            return false;
        }

        // 計算每個點數出現的次數
        Map<PokerRank, Integer> rankCounts = new HashMap<>();
        cards.forEach(
                card -> {
                    PokerRank rank = card.rank();
                    rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
                }
        );

        // 檢查是否有三張相同點數和一對對子
        AtomicBoolean hasThreeInSame = new AtomicBoolean(false);
        AtomicBoolean hasPair = new AtomicBoolean(false);
        rankCounts.values().forEach(
                count -> {
                    if (count == 3) {
                        hasThreeInSame.set(true);
                    } else if (count == 2) {
                        hasPair.set(true);
                    }
                }
        );

        return hasThreeInSame.get() && hasPair.get();
    }

    @Override
    protected boolean patternCompare(@Validated List<PokerCard> topPlay, @Validated List<PokerCard> nextPlay) {
        PokerCard onCompareOfTopPlay = getFullHouseOnCompareCard(topPlay);
        PokerCard onCompareOfNextPlay = getFullHouseOnCompareCard(nextPlay);

        return Big2CardBasicComparator.isNextCardBigger(onCompareOfTopPlay,onCompareOfNextPlay);
    }

    private PokerCard getFullHouseOnCompareCard(@Validated List<PokerCard> fullHouse) {
        Map<PokerRank, Integer> rankCounts = new HashMap<>();
        fullHouse.forEach(
                card -> {
                    PokerRank rank = card.rank();
                    rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
                }
        );

        PokerCard onCompareCard = null;
        for (Map.Entry<PokerRank, Integer> entry : rankCounts.entrySet()) {
            if (entry.getValue() == 3) {
                PokerRank targetRank = entry.getKey();
                onCompareCard = fullHouse.stream()
                        .filter(card -> card.rank() == targetRank)
                        .max(Big2CardBasicComparator.big2Comparator())
                        .orElse(null);
            }
        }

        return onCompareCard;
    }
}
