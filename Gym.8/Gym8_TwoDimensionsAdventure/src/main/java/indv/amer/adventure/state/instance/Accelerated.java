package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.InterruptibleState;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Accelerated extends TimelyState implements InterruptibleState {
    public Accelerated(Creature creature) {
        super(creature, 3);
    }

    private boolean isBonusAction = false;

    @Override
    public void doEffect() {
        if (isBonusAction == false) {
            isBonusAction = true;
            log.info("Accelerating! {} have bonus action!", this.creature.getSymbol());
            this.remaining -= 1;
            this.creature.action();
        } else {
            isBonusAction = false;
        }

    }
}
