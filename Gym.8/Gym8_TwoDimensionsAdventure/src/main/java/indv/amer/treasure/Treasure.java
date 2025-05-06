package indv.amer.treasure;

import indv.amer.MapObject;
import indv.amer.MapPosition;
import indv.amer.creature.Creature;

public abstract class Treasure extends MapObject {
    public Treasure(MapPosition position) {
        super("x", position);
    }

    public abstract void effect(Creature creature);
}
