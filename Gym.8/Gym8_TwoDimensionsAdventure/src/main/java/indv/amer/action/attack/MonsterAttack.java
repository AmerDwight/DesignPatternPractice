package indv.amer.action.attack;

import indv.amer.AdventureMap;
import indv.amer.creature.character.Character;
import indv.amer.creature.Creature;
import indv.amer.creature.monster.Monster;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterAttack extends AttackAction<Monster> {
    @Override
    protected List<Creature<?>> doAttack(Monster monster, AdventureMap map) {
        List<Character> onAttackCharacters = map.getExistCreatures().stream()
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
                .toList();
        if (CollectionUtils.isNotEmpty(onAttackCharacters)) {
            Random random = new Random();
            return List.of(onAttackCharacters.get(random.nextInt(0, onAttackCharacters.size())));
        }
        return new ArrayList<>();
    }
}
