package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.CoreState;
import indv.amer.adventure.state.TimelyState;

public class Invincible extends TimelyState implements CoreState {


    public Invincible(Creature creature) {
        super(creature, 2);
    }

    @Override
    public int recalculateDamage(int originDamage) {
        return 0;
    }

    @Override
    public void doEffect() {
        // do nothing;
    }
}
