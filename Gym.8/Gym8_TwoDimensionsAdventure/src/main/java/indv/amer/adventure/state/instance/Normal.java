package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.DamageRecalculateState;
import indv.amer.adventure.state.State;

public class Normal extends State {
    public Normal(Creature creature) {
        super(creature);
    }

    @Override
    public void checkState() {
    }
}
