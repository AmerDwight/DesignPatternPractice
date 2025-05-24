package indv.amer.adventure.action.attack;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.creature.character.Character;
import indv.amer.adventure.creature.character.CharacterDirection;
import indv.amer.adventure.creature.monster.Monster;
import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.map.MapObject;
import indv.amer.adventure.map.MapPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EruptingAttack extends AttackAction {
    private final int ERUPTING_ATTACK_DAMAGE = 50;

    @Override
    protected List<Creature<?>> doAttack(Creature creature, AdventureMap map) {
        List<MapPosition> onAttackPositions = getOnAttackPositions(creature, map);
        List<Creature<?>> hurtMonsters = new ArrayList<>();
        onAttackPositions.forEach(
                onAttackPosition -> {
                    MapObject mapObject = map.getMapObjectByPosition(onAttackPosition);
                    if (mapObject instanceof Creature onAttackCreature) {
                        onAttackCreature.getHurt(ERUPTING_ATTACK_DAMAGE);
                        hurtMonsters.add(onAttackCreature);
                    }
                }
        );
        return hurtMonsters;
    }


    private List<MapPosition> getOnAttackPositions(Creature attackingCreature, AdventureMap map) {
        return map.getExistCreatures().stream()
                .map(Creature::getPosition)
                .filter(creature -> !creature.equals(attackingCreature.getPosition())).toList();
    }
}
