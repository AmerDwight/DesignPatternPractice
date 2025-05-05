package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.TimelyState;

public class Healing extends TimelyState {
    public Healing(Creature creature) {
        super(creature, 5);
    }

    @Override
    public void doEffect() {
        this.creature.getHeal(30);
    }
}
