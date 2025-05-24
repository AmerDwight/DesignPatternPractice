package indv.amer.adventure.state.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.state.TimelyState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Teleport extends TimelyState {
    public Teleport(Creature creature) {
        super(creature, 2);
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void exitState() {
        AdventureMap map = creature.getMap();
        map.randomlyPutObject(creature);
        log.info("{}'s {} state over", this.creature.getSymbol(), this.getClass().getSimpleName());
        super.exitState();
    }
}
