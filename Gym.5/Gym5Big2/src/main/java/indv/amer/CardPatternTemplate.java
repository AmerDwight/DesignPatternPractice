package indv.amer;

import indv.amer.poker.PokerCard;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class CardPatternTemplate {
    CardPatternTemplate nextTemplate;

    public boolean verify(List<PokerCard> orderedOnCheckPlay) {
        if (this.patternVerify(orderedOnCheckPlay)) {
            return true;
        } else {
            if (nextTemplate != null) {
                return nextTemplate.verify(orderedOnCheckPlay);
            } else {
                return false;
            }
        }
    }

    public boolean compare(List<PokerCard> orderedTopPlay, List<PokerCard> orderedNextPlay) {
        if (this.patternVerify(orderedTopPlay)) {
            // 兩種牌型都一樣才做比較
            if (this.patternVerify(orderedNextPlay)) {
                return this.patternCompare(orderedTopPlay, orderedNextPlay);
            } else {
                return false;
            }
        } else if (nextTemplate != null) {
            return nextTemplate.compare(orderedTopPlay, orderedNextPlay);
        } else {
            return false;
        }
    }

    protected abstract boolean patternVerify(List<PokerCard> orderedOnCheckPlay);

    protected abstract boolean patternCompare(List<PokerCard> orderedTopPlay, List<PokerCard> orderedNextPlay);
}
