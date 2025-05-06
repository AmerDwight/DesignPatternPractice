package indv.amer.treasure.instance;

import indv.amer.MapPosition;
import indv.amer.creature.Creature;
import indv.amer.treasure.Treasure;

public class Poison extends Treasure {
    public Poison(MapPosition position) {
        super(position);
    }

    @Override
    public void effect(Creature creature) {

    }
}
