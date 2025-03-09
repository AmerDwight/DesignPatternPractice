package game.component.constraint;

import game.component.context.GameContext;
import game.component.PlayRecord;
import game.component.card.uno.UnoCard;
import game.component.player.Player;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UnoShowConstraint extends ShowConstraint<UnoCard> {
    @Override
    protected List<UnoCard> getAvailableHand(@NonNull Player<UnoCard> player, @NonNull GameContext<UnoCard> context) {
        Stack<UnoCard> cardHistory = new Stack<>();
        cardHistory.addAll(context.getHistoryCopy()
                .stream()
                .map(PlayRecord<UnoCard>::getCard)
                .toList());
        //假設邏輯：
        //1. 沒人出過卡，則第一人可以任意出
        //2. 假設上家沒有出過卡，則繼續往上家找
        return getAvailableHandByCheckLastCard(player.getHand(),cardHistory);
    }

    private List<UnoCard> getAvailableHandByCheckLastCard(@NonNull List<UnoCard> hands, @NonNull Stack<UnoCard> cardHistory) {
        if (CollectionUtils.isNotEmpty(cardHistory)) {
            UnoCard lastCard = cardHistory.pop();
            if (lastCard != null) {
                return this.compareHands(lastCard, hands);
            } else {
                return this.getAvailableHandByCheckLastCard(hands, cardHistory);
            }
        } else {
            return hands;
        }
    }

    private List<UnoCard> compareHands(UnoCard lastCard, List<UnoCard> hands) {
        List<UnoCard> availableHand = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(hands)){
            hands.forEach(
                    hand -> {
                        if(hand == null ){
                            System.out.println("Hand Null");
                        }
                        if (lastCard.getColor().equals(hand.getColor()) ||
                                lastCard.getNumber().equals(hand.getNumber())) {
                            availableHand.add(hand);
                        }
                    }
            );
        }
        return availableHand;
    }
}
