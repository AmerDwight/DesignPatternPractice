package indv.amer.creature.monster;

import indv.amer.MapPosition;
import indv.amer.action.attack.AttackAction;
import indv.amer.action.attack.MonsterAttack;
import indv.amer.creature.Creature;

public class Monster extends Creature<Monster> {
    public Monster(MapPosition position) {
        super("M", 1, position, new MonsterAttack());
    }

    @Override
    public void action() {

    }
}
