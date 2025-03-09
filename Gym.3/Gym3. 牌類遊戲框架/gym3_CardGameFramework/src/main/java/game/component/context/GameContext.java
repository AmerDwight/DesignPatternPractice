package game.component.context;

import game.component.Deck;
import game.component.PlayRecord;
import game.component.card.Card;
import game.component.player.Player;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

@Getter
public abstract class GameContext<T extends Card> {
    final Integer playerNumber;
    List<Player<T>> players;
    final Integer playerHandStartSize;
    Deck<T> deck = getInitDeck();
    Deck<T> graveyard = new Deck<>();
    Stack<PlayRecord<T>> gameHistory = new Stack<>();

    public GameContext(int _playerNumber, int _playerHandStartSize){
        this.playerNumber = _playerNumber;
        this.playerHandStartSize = _playerHandStartSize;
    }

    public void init(List<Player<T>> players) {
        if (CollectionUtils.isEmpty(players) || players.size() != this.playerNumber) {
            throw new IllegalArgumentException("Player Size is incorrect.");
        }
        this.players = players;
        IntStream.range(0, players.size()).forEach(
                i -> {
                    Player<T> player = players.get(i);
                    player.getPlayerName();
                    System.out.println(String.format("Player %d is %s", i + 1, player.getPlayerName()));
                }
        );
        players.forEach(Player::getPlayerName);

    }

    public Deck<T> getInitDeck() {
        return initNewGameDeck().shuffle();
    }

    protected abstract Deck<T> initNewGameDeck();

    public void initCheck() {
        if (this.playerNumber < 0 ||
                CollectionUtils.isEmpty(this.players) ||
                this.playerHandStartSize < 0 ||
                deck == null) {
            throw new IllegalArgumentException("Init Method is incomplete.");
        }
    }

    public void updateStatus(Player<T> player, T card) {
        if (card != null) {
            this.getGraveyard().put(card);
        }
        gameHistory.add(new PlayRecord<>(player, card));
    }

    public Stack<PlayRecord<T>> getHistoryCopy() {
        Stack<PlayRecord<T>> newStack = new Stack<>();
        newStack.addAll(this.gameHistory);
        return newStack;
    }
}
