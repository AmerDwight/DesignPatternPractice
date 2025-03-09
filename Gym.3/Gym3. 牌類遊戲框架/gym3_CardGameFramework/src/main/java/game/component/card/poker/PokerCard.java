package game.component.card.poker;

import game.component.card.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PokerCard implements Card {
    private final PokerSuit suit;
    private final PokerRank rank;

}
