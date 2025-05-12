package indv.amer.treasure;

import indv.amer.AdventureMap;
import indv.amer.MapObject;
import indv.amer.MapPosition;
import indv.amer.creature.Creature;

public abstract class Treasure extends MapObject {
    public Treasure(MapPosition position, AdventureMap map) {
        super("x", position, map);
    }

    public abstract void effect(Creature creature);
}
