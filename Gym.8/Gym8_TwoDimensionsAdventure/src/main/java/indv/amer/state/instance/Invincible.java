package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.CoreState;
import indv.amer.state.TimelyState;

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
