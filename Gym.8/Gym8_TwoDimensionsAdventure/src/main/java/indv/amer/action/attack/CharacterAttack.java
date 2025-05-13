package indv.amer.action.attack;

import indv.amer.AdventureMap;
import indv.amer.MapObject;
import indv.amer.MapPosition;
import indv.amer.creature.character.Character;
import indv.amer.creature.Creature;
import indv.amer.creature.character.CharacterDirection;
import indv.amer.creature.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class CharacterAttack extends AttackAction<Character> {
    @Override
    protected List<Creature<?>> doAttack(Character character, AdventureMap map) {
        List<MapPosition> onAttackPositions = getOnAttackPositions(character, map);
        List<Creature<?>> hurtMonsters = new ArrayList<>();
        onAttackPositions.forEach(
                onAttackPosition ->{
                    MapObject mapObject = map.getMapObjectByPosition(onAttackPosition);
                    if(mapObject instanceof Monster monster){
                        monster.getHurt(monster.getHP());
                        hurtMonsters.add(monster);
                    }
                }
        );
        return hurtMonsters;
    }



    private List<MapPosition> getOnAttackPositions(Character character, AdventureMap map) {
        MapPosition here = character.getPosition();
        List<MapPosition> onAttackPositions = new ArrayList<>();
        if (character.getDirection().equals(CharacterDirection.UP)) {
            for (int i = here.getDimensionY(); i >= 0; i--) {
                onAttackPositions.add(new MapPosition(here.getDimensionX(), i));
            }
        } else if (character.getDirection().equals(CharacterDirection.DOWN)) {
            for (int i = here.getDimensionY(); i < map.getWidth(); i++) {
                onAttackPositions.add(new MapPosition(here.getDimensionX(), i));
            }
        } else if (character.getDirection().equals(CharacterDirection.LEFT)) {
            for (int i = here.getDimensionX(); i >= 0; i--) {
                onAttackPositions.add(new MapPosition(i, here.getDimensionY()));
            }
        } else if (character.getDirection().equals(CharacterDirection.RIGHT)) {
            for (int i = here.getDimensionY(); i < map.getLength(); i++) {
                onAttackPositions.add(new MapPosition(i, here.getDimensionY()));
            }
        }
        return onAttackPositions;
    }
}
