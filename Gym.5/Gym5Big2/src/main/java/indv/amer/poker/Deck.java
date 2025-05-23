package indv.amer.poker;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

@AllArgsConstructor
public class Deck {
    Stack<PokerCard> cardStack;

    public PokerCard deal() {
        if (CollectionUtils.isNotEmpty(cardStack)) {
            return cardStack.pop();
        }
        return null;
    }

    public void shuffle() {
        if (CollectionUtils.isEmpty(cardStack)) {
            this.cardStack = new Stack<>();
        } else {
            Collections.shuffle(cardStack);
        }
    }

    public static Deck importFromStr(String importStr) {
        if (StringUtils.isBlank(importStr)) {
            throw new IllegalArgumentException("Empty deck String");
        }
        Stack<PokerCard> cardStack = new Stack<>();
        Arrays.stream(importStr.split(" ")).forEach(
                cardStr -> cardStack.push(PokerCard.readCard(cardStr))
        );
        return new Deck(cardStack);
    }
}
