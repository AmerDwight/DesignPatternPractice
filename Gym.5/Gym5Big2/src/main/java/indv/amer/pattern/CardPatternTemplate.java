package indv.amer.pattern;

import indv.amer.Validated;
import indv.amer.poker.PokerCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public abstract class CardPatternTemplate {
    @Getter
    String signature;
    CardPatternTemplate nextTemplate;

    public CardPatternTemplate checkPattern(List<PokerCard> cards){
        if (this.patternVerify(cards)) {
            return this;
        } else {
            if (nextTemplate != null) {
                return nextTemplate.checkPattern(cards);
            } else {
                return null;
            }
        }
    }

    public boolean isNextPlayBigger(@Validated List<PokerCard> topPlay, @Validated List<PokerCard> nextPlay) {
        if (this.patternVerify(topPlay)) {
            // 兩種牌型都一樣才做比較
            if (this.patternVerify(nextPlay)) {
                return this.patternCompare(topPlay, nextPlay);
            } else {
                return false;
            }
        }
        return false;
    }

    protected abstract boolean patternVerify(List<PokerCard> cards);

    protected abstract boolean patternCompare(@Validated List<PokerCard> topPlay,@Validated List<PokerCard> nextPlay);
}
