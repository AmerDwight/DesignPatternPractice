package indv.amer.adventure.action.attack;

import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.creature.Creature;

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
