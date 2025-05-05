package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.CoreState;
import indv.amer.state.State;

public class Normal extends State implements CoreState {
    public Normal(Creature creature) {
        super(creature);
    }

    @Override
    public void checkState() {
    }

    @Override
    public int recalculateDamage(int originDamage) {
        return originDamage;
    }
}
