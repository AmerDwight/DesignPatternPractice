package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.TimelyState;

public class Teleport extends TimelyState {
    public Teleport(Creature creature) {
        super(creature, 1);
    }

    @Override
    public void doEffect() {
        // TODO Get 空地轉移
    }
}
