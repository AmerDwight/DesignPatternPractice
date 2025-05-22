package indv.amer.adventure.creature.monster;

import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.map.MapObject;
import indv.amer.adventure.map.MapPosition;
import indv.amer.adventure.action.attack.MonsterAttack;
import indv.amer.adventure.creature.ActionCommand;
import indv.amer.adventure.creature.character.Character;
import indv.amer.adventure.creature.Creature;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class Monster extends Creature<Monster> {
    public Monster(AdventureMap map) {
        super("M", 1, new MonsterAttack(), map);
    }

    @Override
    protected ActionCommand choseAction(List<ActionCommand> availableActionList) {
        Random random = new Random();
        List<ActionCommand> actionListCopy = new ArrayList<>(availableActionList);
        if (actionListCopy.contains(ActionCommand.ATTACK)) {
            if(ifCharacterAround(this.getPosition(),this.getMap())){
                return ActionCommand.ATTACK;
            }else{
                actionListCopy = actionListCopy.stream()
                        .filter(command -> command!=ActionCommand.ATTACK )
                        .toList();
                return actionListCopy.get(random.nextInt(0,actionListCopy.size()));
            }
        }else{
            return actionListCopy.get(random.nextInt(0,actionListCopy.size()));
        }
    }

    private boolean ifCharacterAround(MapPosition here, AdventureMap map) {
        int hereX = here.getDimensionX();
        int hereY = here.getDimensionY();
        List<MapPosition> aroundPositionList = new ArrayList<>();

        if (hereX - 1 >= 0) {
            aroundPositionList.add(new MapPosition(hereX - 1, hereY));
        }
        if (hereX + 1 <= map.getLength()) {
            aroundPositionList.add(new MapPosition(hereX + 1, hereY));
        }
        if (hereY - 1 >= 0) {
            aroundPositionList.add(new MapPosition(hereX, hereY - 1));
        }
        if (hereX - 1 <= map.getWidth()) {
            aroundPositionList.add(new MapPosition(hereX, hereY + 1));
        }
        if (CollectionUtils.isNotEmpty(aroundPositionList)) {
            for (MapPosition position : aroundPositionList) {
                MapObject onCheckObject = map.getMapObjectByPosition(position);
                if (onCheckObject instanceof Character) {
                    return true;
                }
            }
        }
        return false;
    }
}
