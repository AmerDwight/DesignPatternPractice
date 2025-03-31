package indv.amer.pattern;

import indv.amer.Validated;
import indv.amer.poker.PokerCard;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class CardPatternTemplate {
    CardPatternTemplate nextTemplate;

    public boolean verify(List<PokerCard> cards) {
        if (this.patternVerify(cards)) {
            return true;
        } else {
            if (nextTemplate != null) {
                return nextTemplate.verify(cards);
            } else {
                return false;
            }
        }
    }

    public boolean verifySamePattern(@Validated List<PokerCard> topPlay, @Validated List<PokerCard> nextPlay){
        if (this.patternVerify(topPlay)) {
            // 兩種牌型都一樣才回傳 true
            return this.patternVerify(nextPlay);
        } else if (nextTemplate != null) {
            return nextTemplate.compare(topPlay, nextPlay);
        } else {
            return false;
        }
    }

    public boolean compare(@Validated List<PokerCard> topPlay, @Validated List<PokerCard> nextPlay) {
        if (this.patternVerify(topPlay)) {
            // 兩種牌型都一樣才做比較
            if (this.patternVerify(nextPlay)) {
                return this.patternCompare(topPlay, nextPlay);
            } else {
                return false;
            }
        } else if (nextTemplate != null) {
            return nextTemplate.compare(topPlay, nextPlay);
        } else {
            return false;
        }
    }

    protected abstract boolean patternVerify(List<PokerCard> cards);

    protected abstract boolean patternCompare(@Validated List<PokerCard> topPlay,@Validated List<PokerCard> nextPlay);
}
