package game.component;

import game.CardGameTemplate;
import game.component.card.poker.PokerCard;
import game.component.card.poker.PokerRank;
import game.component.card.poker.PokerSuit;
import game.component.constraint.ShowConstraint;
import game.component.context.ShowDownContext;
import game.component.player.Player;

import java.util.*;

public class ShowDownGame extends CardGameTemplate<PokerCard> {
    final int PLAYER_NUMBER = 4 ;
    final int PLAYER_HAND_INIT_SIZE = 13 ;

    public ShowDownGame() {
        this.gameContext = new ShowDownContext(PLAYER_NUMBER, PLAYER_HAND_INIT_SIZE);
        this.showConstraint = new ShowConstraint<>();
    }

    Map<Player<PokerCard>, Integer> scoreTable = new HashMap<>();

    @Override
    protected Player<PokerCard> checkWinnerAfterEachTurn() {
        // 當每位玩家出玩牌後
        if (this.gameContext.getGameHistory().size() % this.gameContext.getPlayerNumber() == 0) {
            List<PlayRecord<PokerCard>> thisRoundRecord =
                    getStackTopElements(this.gameContext.getGameHistory(), this.gameContext.getPlayerNumber());

            // 公開所有人這輪打的牌
            System.out.println("This Round Record: ");
            thisRoundRecord.forEach(record ->
                    System.out.printf(
                            "Player: %s shows: %s%n \n", record.getPlayer().getPlayerName(), record.getCard().toString()));

            Player<PokerCard> thisRoundWinner = thisRoundRecord.get(
                    comparePokerAndReturnBiggestIndex(thisRoundRecord.stream().map(PlayRecord<PokerCard>::getCard).toList())
            ).getPlayer();

            // 公開本輪勝利者
            System.out.printf("Player: %s has taken this round.\n", thisRoundWinner.getPlayerName());

            // 加分
            int thisRoundWinnerScore = scoreTable.get(thisRoundWinner) != null ? scoreTable.get(thisRoundWinner) : 0;
            scoreTable.put(thisRoundWinner, thisRoundWinnerScore + 1);
        }

        // 若所有人出玩牌
        System.out.println("------------------------------");
        System.out.println("Now Score Table: ");
        System.out.println(scoreTable);
        System.out.println("------------------------------");
        if (this.gameContext.getGameHistory().size() ==
                this.gameContext.getPlayerHandStartSize() * this.gameContext.getPlayerNumber()) {
            return findHighestScoringPlayer(scoreTable);
        }
        return null;
    }

    private int comparePokerAndReturnBiggestIndex(List<PokerCard> cards) {
        if (cards == null || cards.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two cards");
        }

        // 定義階級和花色的比較順序
        Map<PokerRank, Integer> rankOrder = new HashMap<>() {{
            put(PokerRank.TWO, 2);
            put(PokerRank.THREE, 3);
            put(PokerRank.FOUR, 4);
            put(PokerRank.FIVE, 5);
            put(PokerRank.SIX, 6);
            put(PokerRank.SEVEN, 7);
            put(PokerRank.EIGHT, 8);
            put(PokerRank.NINE, 9);
            put(PokerRank.TEN, 10);
            put(PokerRank.JACK, 11);
            put(PokerRank.QUEEN, 12);
            put(PokerRank.KING, 13);
            put(PokerRank.ACE, 14);
        }};

        Map<PokerSuit, Integer> suitOrder = new HashMap<>() {{
            put(PokerSuit.CLUB, 1);
            put(PokerSuit.DIAMOND, 2);
            put(PokerSuit.HEART, 3);
            put(PokerSuit.SPADE, 4);
        }};

        // 比較所有牌，找出最大的牌的索引
        int maxIndex = 0;
        for (int i = 1; i < cards.size(); i++) {
            PokerCard currentMaxCard = cards.get(maxIndex);
            PokerCard compareCard = cards.get(i);

            // 先比較階級
            int rankCompare = rankOrder.get(currentMaxCard.getRank())
                    .compareTo(rankOrder.get(compareCard.getRank()));

            if (rankCompare < 0) {
                // 新的牌階級更大
                maxIndex = i;
            } else if (rankCompare == 0) {
                // 階級相同，比較花色
                int suitCompare = suitOrder.get(currentMaxCard.getSuit())
                        .compareTo(suitOrder.get(compareCard.getSuit()));

                if (suitCompare < 0) {
                    // 新的牌花色更大
                    maxIndex = i;
                }
            }
        }

        return maxIndex;
    }

    private static <K> List<K> getStackTopElements(Stack<K> stack, int n) {
        // 檢查 n 是否合法
        if (n < 0) {
            throw new IllegalArgumentException("N must be non-negative");
        }

        // 創建結果列表
        List<K> topN = new ArrayList<>();

        // 創建臨時 Stack 來保存彈出的元素
        Stack<K> tempStack = new Stack<>();

        // 取 n 和 stack 大小的最小值，避免超出 stack 大小
        int limit = Math.min(n, stack.size());

        // 取前 N 個元素
        for (int i = 0; i < limit; i++) {
            K top = stack.pop();
            topN.add(top);
            tempStack.push(top);
        }

        // 將元素放回原 stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return topN;
    }

    private Player<PokerCard> findHighestScoringPlayer(Map<Player<PokerCard>, Integer> scoreTable) {
        // Check if the map is empty
        if (scoreTable == null || scoreTable.isEmpty()) {
            return null;
        }

        // Method 1: Using Collections.max() with a Comparator
        return Collections.max(scoreTable.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();
    }

}