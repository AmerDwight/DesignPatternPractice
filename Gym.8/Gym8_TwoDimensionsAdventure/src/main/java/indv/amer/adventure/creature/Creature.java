package indv.amer.adventure.creature;

import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.map.MapObject;
import indv.amer.adventure.action.attack.AttackAction;
import indv.amer.adventure.action.move.MoveAction;
import indv.amer.adventure.action.move.SingleStepMove;
import indv.amer.adventure.state.OnHurtReactState;
import indv.amer.adventure.state.instance.Normal;
import indv.amer.adventure.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
public abstract class Creature<T extends Creature<T>> extends MapObject {

    private int HP;
    private AttackAction<T> DEFAULT_ATTACK;
    private AttackAction<T> attackAction;
    private MoveAction moveAction;
    private State state;
    private AdventureMap map;
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

    public Creature(String symbol, int initHP, AttackAction<T> _attackAction, AdventureMap _map) {
        super(symbol);
        this.HP = initHP;
        this.DEFAULT_ATTACK = _attackAction;
        this.attackAction = _attackAction;
        this.moveAction = new SingleStepMove();
        this.state = new Normal(this);
        this.map = _map;
        this.availableActionList = List.of(ActionCommand.values());
    }

    public void changeState(State newState) {
        log.info("{} got state: {}", this.getSymbol(), newState.getClass().getSimpleName());
        this.state = newState;
    }

    public void getHurt(int damage) {
        if (this.isAlive()) {
            if (this.getState() instanceof OnHurtReactState) {
                damage = ((OnHurtReactState) this.getState()).recalculateDamage(damage);
            }
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

    public void resetAttack() {
        this.attackAction = this.DEFAULT_ATTACK;
    }
}
