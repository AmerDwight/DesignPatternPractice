package game;

import game.component.card.Card;
import game.component.player.Player;

import java.util.stream.IntStream;

public abstract class CardGameTemplate<T extends Card> extends AbstractCardGame<T> {
    @Override
    void drawingStage() {
        IntStream.range(0, gameContext.getPlayerHandStartSize())
                .forEach(i -> gameContext.getPlayers()
                        .forEach(player -> player.draw(gameContext.getDeck())));
    }

    @Override
    Player<T> gameStage() {
        Player<T> winner = null;
        while (winner == null) {
            Player<T> thisTurnPlayer = getThisTurnPlayer();
            doPlayerAction(thisTurnPlayer);
            winner = checkWinnerAfterEachTurn();
        }
        return winner;
    }

    protected Player<T> getThisTurnPlayer() {
        return this.gameContext.getPlayers().get(gameContext.getGameHistory().size() % gameContext.getPlayerNumber());
    }
    protected Player<T> getLastTurnPlayer() {
        return this.gameContext.getPlayers().get(gameContext.getGameHistory().size() % gameContext.getPlayerNumber());
    }

    protected void doPlayerAction(Player<T> player) {
        // Generally
        T showedCard = player.show(this.showConstraint.judgement(player, this.gameContext));
        this.gameContext.updateStatus(player, showedCard);
    }

    protected abstract Player<T> checkWinnerAfterEachTurn();
}
