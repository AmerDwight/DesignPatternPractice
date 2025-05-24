package indv.amer.adventure.action.attack;

import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.creature.Creature;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class AttackAction<T extends Creature<T>> {
    public void attack(T creature, AdventureMap map) {
        if (creature == null || !creature.isAlive()) {
            return;
        }
        log.info("{} is attacking!", creature.getSymbol());
        List<Creature<?>> getHurtCreatures = this.doAttack(creature, map);
        getHurtCreatures.forEach(
                hurtCreature -> {
                    log.info("{} is hurt!", hurtCreature.getSymbol());
                    if (!hurtCreature.isAlive()) {
                        log.info("{} is dead!", hurtCreature.getSymbol());
                        map.eliminateMapObject(hurtCreature);
                    }
                }
        );
    }

    protected abstract List<Creature<?>> doAttack(T creature, AdventureMap map);
}
