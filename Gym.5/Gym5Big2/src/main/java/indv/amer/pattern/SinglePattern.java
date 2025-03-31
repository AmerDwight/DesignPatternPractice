package indv.amer.pattern;

import indv.amer.comparator.Big2BasicComparator;
import indv.amer.Validated;
import indv.amer.poker.PokerCard;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class SinglePattern extends CardPatternTemplate {
    public SinglePattern(CardPatternTemplate nextTemplate) {
        super(nextTemplate);
    }

    @Override
    protected boolean patternVerify(List<PokerCard> cards) {
        // 長度只能是1
        return CollectionUtils.isNotEmpty(cards) && cards.size() == 1;
    }

    @Override
    protected boolean patternCompare(@Validated List<PokerCard> topPlay, @Validated List<PokerCard> nextPlay) {
        return Big2BasicComparator.isNextCardBigger(topPlay.get(0), nextPlay.get(0));
    }
}
