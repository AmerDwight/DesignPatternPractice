package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.TimelyState;

public class Accelerated extends TimelyState {
    public Accelerated(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        this.creature.action();
    }
}
