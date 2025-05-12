package indv.amer.action.attack;

import indv.amer.AdventureMap;
import indv.amer.MapPosition;
import indv.amer.creature.character.Character;
import indv.amer.creature.Creature;

import java.util.ArrayList;
import java.util.List;

public class CharacterAttack extends AttackAction<Character> {
    @Override
    protected List<Creature<?>> doAttack(Character character, AdventureMap map) {
        List<MapPosition> onAttackPositions;
        onAttackPositions = getOnAttackPositions(character,map);
    }

    private List<MapPosition> getOnAttackPositions(Character character, AdventureMap map) {
        List<MapPosition> onAttackPositions = new ArrayList<>();
        if(character.get){

        }
    }
}
