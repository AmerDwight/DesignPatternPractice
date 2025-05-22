package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.CoreState;
import indv.amer.adventure.state.State;

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
