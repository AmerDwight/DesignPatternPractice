package indv.amer.pattern;

import indv.amer.Big2CardBasicComparator;
import indv.amer.Validated;
import indv.amer.poker.PokerCard;

import java.util.List;

public class PairPattern extends CardPatternTemplate {
    public PairPattern(CardPatternTemplate nextTemplate) {
        super(nextTemplate);
    }

    @Override
    protected boolean patternVerify(List<PokerCard> cards) {
        if (cards == null || cards.size() != 2) {
            return false;
        }
        return cards.get(0).rank() == cards.get(1).rank();
    }

    @Override
    protected boolean patternCompare(@Validated List<PokerCard> topPlay, @Validated List<PokerCard> nextPlay) {
        return Big2CardBasicComparator.isNextCardBigger(
                topPlay.stream().max(Big2CardBasicComparator.big2Comparator()).orElse(null),
                nextPlay.stream().max(Big2CardBasicComparator.big2Comparator()).orElse(null)
        );
    }
}
