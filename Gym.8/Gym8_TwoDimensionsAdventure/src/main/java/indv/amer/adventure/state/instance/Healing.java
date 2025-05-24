package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Healing extends TimelyState {
    public Healing(Creature creature) {
        super(creature, 5);
    }

    @Override
    public void doEffect() {
        log.info("Healing State: {} healing.", this.creature.getSymbol());
        this.creature.getHeal(30);
    }
}
