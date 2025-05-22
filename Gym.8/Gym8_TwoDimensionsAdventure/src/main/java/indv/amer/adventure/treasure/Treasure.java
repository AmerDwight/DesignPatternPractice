package indv.amer.adventure.treasure;

import indv.amer.adventure.map.MapObject;
import indv.amer.adventure.creature.Creature;

public abstract class Treasure extends MapObject {
    public Treasure() {
        super("x");
    }

    public abstract void effect(Creature creature);
}
