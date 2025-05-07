package indv.amer.action.attack;

import indv.amer.AdventureMap;
import indv.amer.creature.Creature;

import java.util.List;

public abstract class AttackAction<T extends Creature<T>> {
    public void attack(T creature, AdventureMap map) {
        if (creature == null || !creature.isAlive()) {
            return;
        }
        List<Creature<?>> getHurtCreatures = this.doAttack(creature, map);
        getHurtCreatures.forEach(
                hurtCreature -> {
                    if (!hurtCreature.isAlive()) {
                        map.eliminateMapObject(hurtCreature);
                    }
                }
        );
    }

    protected abstract List<Creature<?>> doAttack(T creature, AdventureMap map);
}
