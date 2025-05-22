package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.creature.ActionCommand;
import indv.amer.adventure.state.TimelyState;

import java.util.List;
import java.util.Random;

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
    }

    @Override
    public void exitState() {
        this.creature.setAvailableActionList(List.of(ActionCommand.values()));
        super.exitState();
    }
}
