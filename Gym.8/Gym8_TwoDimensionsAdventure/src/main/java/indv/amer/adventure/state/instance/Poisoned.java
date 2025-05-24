package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Poisoned extends TimelyState {
    public Poisoned(Creature creature) {
        super(creature, 3);
    }

    private final int POISONED_DAMAGE = 15;

    @Override
    public void doEffect() {
        this.creature.setHP(this.creature.getHP() - POISONED_DAMAGE);
        log.info("Poisoned State: {} urrrr... poisoned {} damage, HP = {}",
                this.creature.getSymbol(),
                this.POISONED_DAMAGE,
                this.creature.getHP());

    }
}
