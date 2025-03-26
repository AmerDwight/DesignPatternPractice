package game.component.card.poker;

import lombok.Getter;

@Getter
public enum PokerSuit {
    SPADE("Spade"),
    HEART("Heart"),
    DIAMOND("Diamond"),
    CLUB("Club");

    private final String suitName;

    PokerSuit(String name) {
        this.suitName = name;
    }
}
