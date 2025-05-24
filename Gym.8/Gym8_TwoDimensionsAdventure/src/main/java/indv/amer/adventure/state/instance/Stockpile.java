package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.OnHurtReactState;
import indv.amer.adventure.state.InteruptableState;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stockpile extends TimelyState implements InteruptableState {
    public Stockpile(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void exitState() {
        log.info("{}要去剌剌剌剌", this.creature.getSymbol());
        this.creature.changeState(new Erupting(creature));
    }

    public void turnToNormalSate() {
        this.creature.changeState(new Normal(creature));
    }
}
