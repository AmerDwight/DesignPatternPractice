package game.component.player;

import game.component.card.Card;

import java.util.*;

public class AiPlayer<T extends Card> extends Player<T> {

    Random random = new Random();

    @Override
    public int pickCardStrategy(int handSize) {
        if (handSize > 0) {
            return random.nextInt(1,handSize+1);
        } else {
            return 0;
        }
    }

    @Override
    protected String insertName() {
        return RandomPlayerNameService.generateRandomName();
    }
}
