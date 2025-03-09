package game.component.player;

import game.component.Deck;
import game.component.card.Card;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player<T extends Card> {
    protected String playerName = "";
    private List<T> hand;

    public T show(Collection<T> availableHand) {
        if (CollectionUtils.isNotEmpty(availableHand)) {
            // 轉換成 List類型，提供玩家選擇
            List<T> listHand = new ArrayList<>(availableHand);
            System.out.println("Now You Have Following Cards Can Show ：");

            // 顯示玩家有哪些選擇
            for (int i = 0; i < availableHand.size(); i++) {
                System.out.println("Card " + (i + 1) + " = " + listHand.get(i).toString());
            }

            // 請玩家選擇
            int playerChose = pickCardStrategy(availableHand.size());

            // 從手牌中刪除該張卡牌
            T onShowCard = listHand.remove(playerChose - 1); // index offset
            this.getHand().remove(onShowCard);
            return onShowCard;

        } else {
            System.out.println("You don't have a show-able card.");
            return null;
        }
    }

    public abstract int pickCardStrategy(int handSize);

    public String getPlayerName() {
        if (StringUtils.isBlank(this.playerName)) {
            this.playerName = this.insertName();
        }
        return this.playerName;
    }

    protected abstract String insertName();

    public void draw(Deck<T> deck) {
        this.getHand().add(deck.withDraw());
    }

    public List<T> getHand() {
        if (CollectionUtils.isEmpty(this.hand)) {
            this.hand = new ArrayList<>();
        }
        return this.hand;
    }

    @Override
    public String toString() {
        return this.getPlayerName() + " ";
    }
}
