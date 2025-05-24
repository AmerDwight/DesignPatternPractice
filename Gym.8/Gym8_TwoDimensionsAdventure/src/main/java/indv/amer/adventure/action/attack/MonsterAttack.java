package indv.amer.adventure.action.attack;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.creature.character.Character;
import indv.amer.adventure.creature.monster.Monster;
import indv.amer.adventure.map.AdventureMap;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonsterAttack extends AttackAction<Monster> {
    @Override
    protected List<Creature<?>> doAttack(Monster monster, AdventureMap map) {
        List<Character> onAttackCharacters = new ArrayList<>(map.getExistCreatures().stream()
                .filter(mapCreature -> mapCreature instanceof Character)
                .filter(character -> !character.getPosition().equals(monster.getPosition()))
                .filter(character ->
                        (Math.abs(
                                character.getPosition().getDimensionX() -
                                        monster.getPosition().getDimensionX()) == 1) ||
                                (Math.abs(
                                        character.getPosition().getDimensionY() -
                                                monster.getPosition().getDimensionY()) == 1))
                .map(mapCreature -> (Character) mapCreature)
                .toList());
        if (CollectionUtils.isNotEmpty(onAttackCharacters)) {
            Collections.shuffle(onAttackCharacters);
            Character onAttackCharacter = onAttackCharacters.get(0);
            onAttackCharacter.getHurt(50);
            return List.of(onAttackCharacter);
        }
        return new ArrayList<>();
    }
}
