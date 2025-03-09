package game.component.context;

import game.component.Deck;
import game.component.card.poker.PokerCard;
import game.component.card.poker.PokerRank;
import game.component.card.poker.PokerSuit;

import java.util.Collections;

public class ShowDownContext extends GameContext<PokerCard> {
    public ShowDownContext(int _playerNumber, int _playerHandStartSize) {
        super(_playerNumber, _playerHandStartSize);
    }

    @Override
    protected Deck<PokerCard> initNewGameDeck() {
        Deck<PokerCard> newShowDownDeck = new Deck<>();
        for (PokerSuit pokerSuit : PokerSuit.values()) {
            for (PokerRank pokerRank : PokerRank.values()) {
                newShowDownDeck.put(new PokerCard(pokerSuit, pokerRank));
            }
        }
        return newShowDownDeck;
    }
}
