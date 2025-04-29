package indv.amer.creature.state;

import indv.amer.creature.Creature;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class State {
    public static int EXISTS_ROUNDS = 1 ;
    private Creature creature;

    public abstract void checkState();
    public void exitState(){
        creature.changeState(new Normal(creature));
    }
}
