package indv.amer.adventure.state;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.instance.Normal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public abstract class State {
    public static int EXISTS_ROUNDS = 1;
    protected Creature creature;

    public abstract void checkState();

    public void exitState() {
        log.info("{}'s {} state over", this.creature.getSymbol(), this.getClass().getSimpleName());
        creature.changeState(new Normal(creature));
    }
}
