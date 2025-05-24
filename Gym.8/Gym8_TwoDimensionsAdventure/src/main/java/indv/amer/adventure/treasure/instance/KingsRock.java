package indv.amer.adventure.treasure.instance;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.state.instance.Stockpile;
import indv.amer.adventure.treasure.Treasure;

public class KingsRock extends Treasure {
    @Override
    public void effect(Creature creature) {
        creature.changeState(new Stockpile(creature));
    }
}
