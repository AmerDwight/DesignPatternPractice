package indv.amer.poker;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.IntStream;

public record PokerCard(PokerSuit suit, PokerRank rank) {
    @Override
    public String toString() {
        return String.format("%s[%s]", this.suit.getSymbol(), this.rank.getRank());
    }

    public static PokerCard readCard(String cardStr) {
        String prefixBracket = "[";
        String postfixBracket = "]";
        if (StringUtils.isBlank(cardStr) || cardStr.length() != cardStr.indexOf(postfixBracket)+1) {
            throw new IllegalArgumentException("Card String: " + cardStr + " is illegal.");
        } else {
            return new PokerCard(
                    PokerSuit.readSuitSymbol(cardStr.substring(0, 1)),
                    PokerRank.readRank(cardStr.substring(cardStr.indexOf(prefixBracket)+1, cardStr.indexOf(postfixBracket)))
            );
        }
    }

    public static String getPokerListString(List<PokerCard> cards) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(cards)) {
            IntStream.range(0, cards.size()).forEach(
                    i -> {
                        if (i > 0) {
                            sb.append(" ");
                        }
                        sb.append(cards.get(i).toString());
                    }
            );
        }
        return sb.toString();
    }
}

