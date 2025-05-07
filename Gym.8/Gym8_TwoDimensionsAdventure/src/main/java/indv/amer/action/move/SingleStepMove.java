package indv.amer.action.move;

import indv.amer.MapObject;
import indv.amer.MapPosition;
import indv.amer.creature.ActionCommand;

public class SingleStepMove extends MoveAction {
    @Override
    protected MapPosition getMoveToPosition(MapObject mapObject, ActionCommand moveAction) {
        int nextX = mapObject.getPosition().getDimensionX();
        int nextY = mapObject.getPosition().getDimensionY();

        switch (moveAction) {
            case MOVE_UP:
                nextY += 1;
                break;
            case MOVE_DOWN:
                nextY -= 1;
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
