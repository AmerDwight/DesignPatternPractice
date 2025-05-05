package indv.amer.state.instance;

import indv.amer.creature.Creature;
import indv.amer.creature.CreatureActionCommand;
import indv.amer.state.TimelyState;

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
            this.creature.setAvailableActionList(List.of(CreatureActionCommand.MOVE_UP, CreatureActionCommand.MOVE_DOWN));
        } else {
            this.creature.setAvailableActionList(List.of(CreatureActionCommand.MOVE_LEFT, CreatureActionCommand.MOVE_RIGHT));
        }
    }

    @Override
    public void exitState() {
        this.creature.setAvailableActionList(List.of(CreatureActionCommand.values()));
        super.exitState();
    }
}
