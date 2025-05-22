package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;

public class Accelerated extends TimelyState {
    public Accelerated(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        this.creature.action();
    }
}
