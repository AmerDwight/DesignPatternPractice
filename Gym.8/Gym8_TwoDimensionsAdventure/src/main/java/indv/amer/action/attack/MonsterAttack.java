package indv.amer.action.attack;

import indv.amer.AdventureMap;
import indv.amer.creature.Creature;
import indv.amer.creature.monster.Monster;

import java.util.List;

public class MonsterAttack extends AttackAction<Monster>{
    @Override
    protected List<Creature<?>> doAttack(Monster creature, AdventureMap map) {
        return List.of();
    }
}
