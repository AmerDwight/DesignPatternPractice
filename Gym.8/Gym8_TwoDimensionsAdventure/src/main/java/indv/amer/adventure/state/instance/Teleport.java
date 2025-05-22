package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;

public class Teleport extends TimelyState {
    public Teleport(Creature creature) {
        super(creature, 1);
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void exitState() {
        // TODO TELEPORT
        super.exitState();
    }
}
