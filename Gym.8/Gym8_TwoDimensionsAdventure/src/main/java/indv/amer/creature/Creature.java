package indv.amer.creature;

import indv.amer.AdventureMap;
import indv.amer.MapObject;
import indv.amer.MapPosition;
import indv.amer.action.attack.AttackAction;
import indv.amer.action.move.MoveAction;
import indv.amer.action.move.SingleStepMove;
import indv.amer.state.instance.Normal;
import indv.amer.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
public abstract class Creature<T extends Creature<T>> extends MapObject {

    private int HP;
    private AttackAction<T> attackAction;
    private MoveAction moveAction;
    private State state;
    private List<ActionCommand> availableActionList;

    public void action() {
        ActionCommand chosenCommand;
        do {
            chosenCommand = this.choseAction(availableActionList);
        } while (!availableActionList.contains(chosenCommand));

        if (chosenCommand == ActionCommand.ATTACK) {
            attackAction.attack((T) this, this.getMap());
        } else {
            moveAction.move(this, chosenCommand, this.getMap());
        }
    }

    protected abstract ActionCommand choseAction(List<ActionCommand> availableActionList);

    public Creature(String symbol, int initHP, MapPosition position, AttackAction<T> attackAction, AdventureMap map) {
        super(symbol, position, map);
        this.HP = initHP;
        this.attackAction = attackAction;
        this.moveAction = new SingleStepMove();
        this.state = new Normal(this);
        this.availableActionList = List.of(ActionCommand.values());
    }

    public void changeState(State newState) {
        this.state = newState;
    }

    public void getHurt(int damage) {
        if (this.isAlive()) {
            this.HP -= damage;
            if (this.HP < 0) {
                log.info("{} is dead!", this.getClass().getSimpleName());
            }
        } else {
            log.info("We don't attack on dead body, are you hentai?");
        }
    }

    public void getHeal(int milk) {
        if (this.isAlive()) {
            this.HP += milk;
        } else {
            log.info("We don't do anything on dead body, are you hentai?");
        }
    }

    public boolean isAlive() {
        return this.HP > 0;
    }
}
