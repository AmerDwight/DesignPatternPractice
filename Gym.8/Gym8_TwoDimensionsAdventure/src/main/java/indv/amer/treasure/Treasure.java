package indv.amer.treasure;

import indv.amer.MapObject;
import indv.amer.creature.Creature;

public abstract class Treasure extends MapObject {
    public Treasure() {
        super("x");
    }

    public abstract void effect(Creature creature);
}
