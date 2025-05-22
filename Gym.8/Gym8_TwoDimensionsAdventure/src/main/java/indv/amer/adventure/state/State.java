package indv.amer.adventure.state;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.instance.Normal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class State {
    public static int EXISTS_ROUNDS = 1;
    protected Creature creature;

    public abstract void checkState();

    public void exitState() {
        creature.changeState(new Normal(creature));
    }
}
