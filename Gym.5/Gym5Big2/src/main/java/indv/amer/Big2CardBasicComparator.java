package indv.amer;

import indv.amer.poker.PokerCard;
import indv.amer.poker.PokerRank;
import indv.amer.poker.PokerSuit;

import java.util.Comparator;

public class Big2CardBasicComparator implements Comparator<PokerCard> {
    // 定義大老二的牌面順序（從小到大）
    private static final PokerRank[] RANK_ORDER = {
            PokerRank.THREE, PokerRank.FOUR, PokerRank.FIVE, PokerRank.SIX,
            PokerRank.SEVEN, PokerRank.EIGHT, PokerRank.NINE, PokerRank.TEN,
            PokerRank.JACK, PokerRank.QUEEN, PokerRank.KING, PokerRank.ACE,
            PokerRank.TWO
    };

    // 定義花色順序（從小到大）
    private static final PokerSuit[] SUIT_ORDER = {
            PokerSuit.CLUB, PokerSuit.DIAMOND, PokerSuit.HEART, PokerSuit.SPADE
    };

    @Override
    public int compare(PokerCard card1, PokerCard card2) {
        // 首先比較牌面大小
        int rankCompare = compareRank(card1.rank(), card2.rank());

        // 如果牌面大小相同，則比較花色
        if (rankCompare == 0) {
            return compareSuit(card1.suit(), card2.suit());
        }

        return rankCompare;
    }

    private int compareRank(PokerRank rank1, PokerRank rank2) {
        return Integer.compare(
                getIndexInRankOrder(rank1),
                getIndexInRankOrder(rank2)
        );
    }

    private int compareSuit(PokerSuit suit1, PokerSuit suit2) {
        return Integer.compare(
                getIndexInSuitOrder(suit1),
                getIndexInSuitOrder(suit2)
        );
    }

    private int getIndexInRankOrder(PokerRank rank) {
        for (int i = 0; i < RANK_ORDER.length; i++) {
            if (RANK_ORDER[i] == rank) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unknown rank: " + rank);
    }

    private int getIndexInSuitOrder(PokerSuit suit) {
        for (int i = 0; i < SUIT_ORDER.length; i++) {
            if (SUIT_ORDER[i] == suit) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unknown suit: " + suit);
    }

    public static Comparator<PokerCard> big2Comparator() {
        return new Big2CardBasicComparator();
    }
}
