package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.OnHurtReactState;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Accelerated extends TimelyState implements OnHurtReactState {
    public Accelerated(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        this.creature.action();
    }

    @Override
    public int recalculateDamage(int originDamage) {
        if (originDamage > 0) {
            log.info("{} is hurt, cancel accelerated state.",this.creature.getSymbol());
            this.creature.changeState(new Normal(this.creature));
        }
        return originDamage;
    }
}
