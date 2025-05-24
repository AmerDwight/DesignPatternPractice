package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.OnHurtReactState;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stockpile extends TimelyState implements OnHurtReactState {
    public Stockpile(Creature creature) {
        super(creature, 2);
    }

    @Override
    public int recalculateDamage(int originDamage) {
        if (originDamage > 0) {
            log.info("{} is hurt, cancel accelerated state.",this.creature.getSymbol());
            this.creature.changeState(new Normal(creature));
        }
        return originDamage;
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void exitState() {
        log.info("{}要去剌剌剌剌", this.creature.getSymbol());
        this.creature.changeState(new Erupting(creature));
    }

    public void turnToNormalSate() {
        this.creature.changeState(new Normal(creature));
    }
}
