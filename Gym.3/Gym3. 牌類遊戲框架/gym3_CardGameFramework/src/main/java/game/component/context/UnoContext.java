package game.component.context;

import game.component.Deck;
import game.component.card.uno.UnoCard;
import game.component.card.uno.UnoCardColor;
import game.component.card.uno.UnoCardNumber;

import java.util.Collections;

public class UnoContext extends GameContext<UnoCard> {
    public UnoContext(int _playerNumber, int _playerHandStartSize) {
        super(_playerNumber, _playerHandStartSize);
    }

    @Override
    protected Deck<UnoCard> initNewGameDeck() {
        Deck<UnoCard> newUnoDeck = new Deck<>();
        for (UnoCardColor unoColor : UnoCardColor.values()) {
            for (UnoCardNumber unoNumber : UnoCardNumber.values()) {
                newUnoDeck.put(new UnoCard(unoColor, unoNumber));
            }
        }
        return newUnoDeck;
    }
}
