package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.creature.ActionCommand;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class Orderless extends TimelyState {
    private final Random random = new Random();

    public Orderless(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {
        if (this.random.nextDouble() > 0.5) {
            this.creature.setAvailableActionList(List.of(ActionCommand.MOVE_UP, ActionCommand.MOVE_DOWN));
        } else {
            this.creature.setAvailableActionList(List.of(ActionCommand.MOVE_LEFT, ActionCommand.MOVE_RIGHT));
        }
        log.info("By Orderless state, {} can only do {}", this.creature.getSymbol(), this.creature.getAvailableActionList());
    }

    @Override
    public void exitState() {
        log.info("{}'s {} state over", this.creature.getSymbol(), this.getClass().getSimpleName());
        this.creature.setAvailableActionList(List.of(ActionCommand.values()));
        super.exitState();
    }
}
