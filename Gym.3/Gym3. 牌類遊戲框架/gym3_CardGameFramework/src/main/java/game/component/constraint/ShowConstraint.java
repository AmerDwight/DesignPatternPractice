package game.component.constraint;

import game.component.context.GameContext;
import game.component.card.Card;
import game.component.player.Player;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ShowConstraint<T extends Card> {
    public List<T> judgement(Player<T> player, GameContext<T> context) {
        if (player != null && CollectionUtils.isNotEmpty(player.getHand())) {
            return getAvailableHand(player, context);
        } else {
            return new ArrayList<>();
        }
    }

    protected List<T> getAvailableHand(@NonNull Player<T> player,@NonNull GameContext<T> context) {
        return new ArrayList<>(player.getHand());
    }
}
