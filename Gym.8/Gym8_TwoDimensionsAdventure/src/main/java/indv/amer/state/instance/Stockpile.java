package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.state.CoreState;
import indv.amer.state.TimelyState;

public class Stockpile extends TimelyState implements CoreState {
    public Stockpile(Creature creature) {
        super(creature, 2);
    }

    @Override
    public int recalculateDamage(int originDamage) {
        this.turnToNormalSate();
        return originDamage;
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void exitState() {
        this.creature.changeState(new Erupting(creature));
    }

    public void turnToNormalSate() {
        this.creature.changeState(new Normal(creature));
    }
}
