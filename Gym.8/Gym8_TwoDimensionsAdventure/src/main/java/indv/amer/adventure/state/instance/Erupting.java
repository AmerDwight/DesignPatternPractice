package indv.amer.adventure.state.instance;

import indv.amer.adventure.action.attack.EruptingAttack;
import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Erupting extends TimelyState {
    public Erupting(Creature creature) {
        super(creature, 3);
    }

    @Override
    public void enterState() {
        if (!(this.creature.getAttackAction() instanceof EruptingAttack)) {
            log.info("{} change attack method: {}", this.creature.getSymbol(), EruptingAttack.class.getSimpleName());
            this.creature.setAttackAction(new EruptingAttack());
        }
    }

    @Override
    public void doEffect() {
        // Leave Blank
    }

    @Override
    public void exitState() {
        this.creature.resetAttack();
        log.info("{} reset default attack method.", this.creature.getSymbol());
        this.creature.changeState(new Teleport(creature));
    }
}
