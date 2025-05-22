package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;

public class Poisoned extends TimelyState {
    public Poisoned(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        this.creature.getHurt(15);
    }
}
