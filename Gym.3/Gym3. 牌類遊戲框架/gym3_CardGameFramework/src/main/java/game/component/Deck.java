package game.component;

import game.component.card.Card;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

@NoArgsConstructor
@AllArgsConstructor
public class Deck<T extends Card> {
    private Stack<T> stack;

    public void put(T card) {
        this.getCardStack().push(card);
    }

    public T withDraw() {
        return stack.pop();
    }

    public Stack<T> withDrawAll() {
        Stack<T> newStack = new Stack<>();
        // 複製出來
        newStack.addAll(stack);

        // 清空
        this.stack = new Stack<>();

        return newStack;
    }

    public Deck<T> shuffle() {
        Collections.shuffle(this.getCardStack());
        return this;
    }

    public void importFrom(Collection<T> cards) {
        Stack<T> cardStack = this.getCardStack();
        cardStack.addAll(cards);
        Collections.shuffle(cardStack);
    }

    public Stack<T> getCardStack() {
        if (CollectionUtils.isEmpty(this.stack)) {
            this.stack = new Stack<>();
        }
        return this.stack;
    }
}
