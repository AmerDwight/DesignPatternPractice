package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;

public class Erupting extends TimelyState {
    public Erupting(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        // TODO 置換攻擊招
    }

    @Override
    public void exitState() {
        // TODO 恢復攻擊招
        this.creature.changeState(new Teleport(creature));
    }
}
