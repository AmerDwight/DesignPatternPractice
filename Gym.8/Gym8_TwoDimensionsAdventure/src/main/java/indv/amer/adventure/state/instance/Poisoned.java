package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Poisoned extends TimelyState {
    public Poisoned(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        log.info("Poisoned State: {} urrrr...", this.creature.getSymbol());
        this.creature.getHurt(15);
    }
}
