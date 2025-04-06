package indv.amer.pattern;

import indv.amer.poker.PokerCard;
import indv.amer.poker.PokerRank;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class StraightPattern extends CardPatternTemplate {
    public StraightPattern(CardPatternTemplate nextTemplate) {
        super(PatternLibrary.Straight.getChinese(), nextTemplate);
    }

    @Override
    protected boolean patternVerify(List<PokerCard> cards) {
        if (cards == null || cards.size() < 5) {
            return false;
        }
        Map<PokerRank, Integer> rankCounts = new HashMap<>();
        cards.forEach(
                card -> {
                    PokerRank rank = card.rank();
                    rankCounts.put(rank, rankCounts.getOrDefault(rank, 0));
                }
        );

        List<PokerRank> rankValues =
                cards.stream()
                        .map(PokerCard::rank)
                        .distinct()
                        .collect(Collectors.toCollection(ArrayList::new));
        ;

        // 檢查是否Rank都不一樣
        if (rankValues.size() != 5) {
            return false;
        }

        // 檢查是否是順子
        return checkNormalStraight(rankValues) || checkBackLoopStraight(rankValues);
    }

    @Override
    protected boolean patternCompare(List<PokerCard> topPlay, List<PokerCard> nextPlay) {
        return false;
    }

    private boolean checkNormalStraight(List<PokerRank> ranks) {
        List<Integer> orderedRanksInNumber =
                ranks.stream()
                        .map(this::getNormalRankValue)
                        .sorted(Integer::compareTo)
                        .toList();
        return checkSequential(orderedRanksInNumber);
    }

    private boolean checkBackLoopStraight(List<PokerRank> ranks) {
        List<Integer> orderedRanksInNumber =
                ranks.stream()
                        .map(this::getBackLoopRankValue)
                        .sorted(Integer::compareTo)
                        .toList();
        return checkSequential(orderedRanksInNumber);
    }

    private boolean checkSequential(List<Integer> orderedRanksInNumber) {
        log.debug("CheckSequential： {}", orderedRanksInNumber);
        for (int i = 0; i < orderedRanksInNumber.size() - 1; i++) {
            if (orderedRanksInNumber.get(i + 1) - orderedRanksInNumber.get(i) != 1) {
                log.debug("Not sequential at index {}: {} and {}", i,
                        orderedRanksInNumber.get(i), orderedRanksInNumber.get(i + 1));
                return false;
            }
        }
        return true;
    }


    private int getNormalRankValue(PokerRank rank) {
        return switch (rank) {
            case ACE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
        };
    }

    private int getBackLoopRankValue(PokerRank rank) {
        return switch (rank) {
            case ACE -> 14;
            case TWO -> 15;
            case THREE -> 16;
            case FOUR -> 17;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
        };
    }

}
