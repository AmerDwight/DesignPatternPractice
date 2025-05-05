package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.TimelyState;

public class Poisoned extends TimelyState {
    public Poisoned(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        this.creature.getHurt(15);
    }
}
