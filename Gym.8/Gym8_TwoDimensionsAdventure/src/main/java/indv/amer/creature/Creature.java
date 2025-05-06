package indv.amer.creature;

import indv.amer.AdventureMap;
import indv.amer.MapObject;
import indv.amer.MapPosition;
import indv.amer.behavior.attack.Attack;
import indv.amer.state.instance.Normal;
import indv.amer.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class Creature<T extends Creature<T>> extends MapObject {

    private String name;
    private int HP;
    private Attack<T> attack;
    @Getter
    private boolean isAlive;
    private State state = new Normal(this);
    @Setter
    private List<CreatureActionCommand> availableActionList = List.of(CreatureActionCommand.values());

    public abstract void action();

    public Creature(String symbol, String _name, int initHP, MapPosition position, Attack<T> attack) {
        super(symbol, position);
        this.name = _name;
        this.HP = initHP;
        this.attack = attack;
        this.isAlive = true;
    }

    public void changeState(State newState) {
        this.state = newState;
    }

    public void getHurt(int damage) {
        if (this.isAlive) {
            this.HP -= damage;
            if (this.HP < 0) {
                this.isAlive = false;
                log.info("{} is dead!", this.name);
            }
        } else {
            log.info("We don't attack on dead body, are you hentai?");
        }
    }

    public void getHeal(int milk) {
        if (this.isAlive) {
            this.HP += milk;
        } else {
            log.info("We don't do anything on dead body, are you hentai?");
        }
    }
}
