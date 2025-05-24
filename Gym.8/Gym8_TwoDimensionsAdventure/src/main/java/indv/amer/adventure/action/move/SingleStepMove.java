package indv.amer.adventure.action.move;

import indv.amer.adventure.map.MapObject;
import indv.amer.adventure.map.MapPosition;
import indv.amer.adventure.creature.ActionCommand;

public class SingleStepMove extends MoveAction {
    @Override
    protected MapPosition getMoveToPosition(MapObject mapObject, ActionCommand moveAction) {
        int nextX = mapObject.getPosition().getDimensionX();
        int nextY = mapObject.getPosition().getDimensionY();

        switch (moveAction) {
            case MOVE_UP:
                nextY -= 1;
                break;
            case MOVE_DOWN:
                nextY += 1;
                break;
            case MOVE_LEFT:
                nextX -= 1;
                break;
            case MOVE_RIGHT:
                nextX += 1;
                break;
        }
        return new MapPosition(nextX, nextY);
    }
}
