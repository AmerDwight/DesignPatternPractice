package game.component;

import game.component.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import game.component.player.Player;

@Getter
@AllArgsConstructor
public class PlayRecord <T extends Card>{
    private Player<T> player;
    private T card;
}
