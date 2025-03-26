package indv.amer.poker;

import org.apache.commons.lang3.StringUtils;

public record PokerCard(PokerSuit suit, PokerRank rank) {
    @Override
    public String toString() {
        return String.format("%s[%s]", this.suit.getSymbol(), this.rank.getRank());
    }

    public static PokerCard readCard(String cardStr) {
        if (StringUtils.isBlank(cardStr) || cardStr.length() != 4) {
            throw new IllegalArgumentException("Card String: " + cardStr + " is illegal.");
        } else {
            return new PokerCard(
                    PokerSuit.readSuitSymbol(cardStr.substring(0, 1)),
                    PokerRank.readRank(cardStr.substring(3, 4))
            );
        }
    }
}

