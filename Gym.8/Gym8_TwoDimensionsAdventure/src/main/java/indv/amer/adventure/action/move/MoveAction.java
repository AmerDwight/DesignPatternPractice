package indv.amer.adventure.action.move;

import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.map.MapObject;
import indv.amer.adventure.map.MapPosition;
import indv.amer.adventure.creature.ActionCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class MoveAction {
    public final static List<ActionCommand> MOVE_ACTIONS =
            List.of(ActionCommand.MOVE_UP,
                    ActionCommand.MOVE_DOWN,
                    ActionCommand.MOVE_LEFT,
                    ActionCommand.MOVE_DOWN);

    public void move(MapObject mapObject, ActionCommand moveAction, AdventureMap map) {
        if (!MOVE_ACTIONS.contains(moveAction)) {
            log.error("{} is not a move action.", moveAction.name());
            throw new IllegalArgumentException();
        }
        map.moveObject(mapObject, this.getMoveToPosition(mapObject, moveAction));

    }

    protected abstract MapPosition getMoveToPosition(MapObject mapObject, ActionCommand moveAction);
}
