package indv.amer;

import indv.amer.pattern.CardPatternTemplate;
import indv.amer.player.Player;
import indv.amer.poker.PokerCard;
import lombok.Data;

import java.util.List;

@Data
public class Round {
    private List<PokerCard> topPlay;
    private int topPlayerIndex;
    private int nowPlayerIndex;
    private  CardPatternTemplate nowPattern;

    public Round(int firstPlayerIndex) {
        this.topPlayerIndex = firstPlayerIndex;
        this.nowPlayerIndex = firstPlayerIndex;
    }

}
