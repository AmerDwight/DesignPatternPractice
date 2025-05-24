package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.DamageRecalculateState;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Invincible extends TimelyState implements DamageRecalculateState {


    public Invincible(Creature creature) {
        super(creature, 2);
    }

    @Override
    public int recalculateDamage(int originDamage) {
        log.info("{} is invincible, zero damage.", this.creature.getSymbol());
        return 0;
    }

    @Override
    public void doEffect() {
        // do nothing;
    }
}
