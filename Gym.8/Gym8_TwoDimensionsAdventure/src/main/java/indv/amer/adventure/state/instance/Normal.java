package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.OnHurtReactState;
import indv.amer.adventure.state.State;

public class Normal extends State implements OnHurtReactState {
    public Normal(Creature creature) {
        super(creature);
    }

    @Override
    public void checkState() {
    }

    @Override
    public int recalculateDamage(int originDamage) {
        return originDamage;
    }
}
