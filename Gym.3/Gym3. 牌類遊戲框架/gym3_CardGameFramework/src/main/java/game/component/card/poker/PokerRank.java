package game.component.card.poker;


import lombok.Getter;

@Getter
public enum PokerRank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String rank;

    PokerRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank;
    }
}