package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;

public class Healing extends TimelyState {
    public Healing(Creature creature) {
        super(creature, 5);
    }

    @Override
    public void doEffect() {
        this.creature.getHeal(30);
    }
}
