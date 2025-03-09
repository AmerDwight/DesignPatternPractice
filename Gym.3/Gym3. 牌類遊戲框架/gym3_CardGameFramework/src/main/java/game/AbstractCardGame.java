package game;

import game.component.constraint.ShowConstraint;
import game.component.context.GameContext;
import game.component.card.Card;
import game.component.player.Player;

import java.util.List;

public abstract class AbstractCardGame<T extends Card> {
    protected ShowConstraint<T> showConstraint;
    protected GameContext<T> gameContext;

    protected void init(List<Player<T>> players) {
        System.out.println("準備牌桌...");
        System.out.println("請各位自我介紹...");
        this.gameContext.init(players);
        this.gameContext.initCheck();
    }

    abstract void drawingStage();

    abstract Player<T> gameStage();

    public void gameStart(List<Player<T>> players) {
        this.init(players);
        this.drawingStage();
        Player<T> winner = this.gameStage();
        System.out.printf("Winner is Player: %s, Congrats!! \n", winner.getPlayerName());
    }

}
