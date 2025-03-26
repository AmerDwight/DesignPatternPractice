package indv.amer.poker;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
public class Card {
    private final PokerSuit suit;
    private final PokerRank rank;

    @Override
    public String toString() {
        return String.format("%s[%s]", this.suit.getSymbol(), this.rank.getRank());
    }

    public static Card readCard(String cardStr) {
        if (StringUtils.isBlank(cardStr) || cardStr.length() != 4) {
            throw new IllegalArgumentException("Card String: " + cardStr + " is illegal.");
        } else {
            return new Card(
                    PokerSuit.readSuitSymbol(cardStr.substring(0, 1)),
                    PokerRank.readRank(cardStr.substring(3, 4))
            );
        }
    }
}

