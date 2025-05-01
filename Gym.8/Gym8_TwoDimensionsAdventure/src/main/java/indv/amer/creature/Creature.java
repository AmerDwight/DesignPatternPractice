package indv.amer.creature;

import indv.amer.AdventureMap;
import indv.amer.MapObject;
import indv.amer.creature.state.Normal;
import indv.amer.creature.state.State;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Creature extends MapObject {

    private String name;
    private int HP;
    private boolean isAlive;
    private State state = new Normal(this);

    public abstract void action(AdventureMap map);

    public Creature(String symbol, String _name, int initHP) {
        super(symbol);
        this.name = _name;
        this.HP = initHP;
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
        }else{
            log.info("We don't do anything on dead body, are you hentai?");
        }
    }
}
