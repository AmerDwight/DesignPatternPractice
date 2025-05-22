package indv.amer.adventure.creature.character;

import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.CommandReader;
import indv.amer.adventure.action.attack.CharacterAttack;
import indv.amer.adventure.creature.ActionCommand;
import indv.amer.adventure.creature.Creature;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Character extends Creature<Character> {
    CommandReader commandReader;
    @Getter
    CharacterDirection direction;

    public Character(int initHP, AdventureMap map, CommandReader _commandReader) {
        super(CharacterDirection.getRandomDirection().getDirectionSymbol(), initHP, new CharacterAttack(), map);
        this.commandReader = _commandReader;
        direction = CharacterDirection.getBySymbol(this.getSymbol());
    }

    @Override
    protected ActionCommand choseAction(List<ActionCommand> availableActionList) {
        ActionCommand chosenAction;

        log.info("Please choose a valid action from : {}", availableActionList);
        chosenAction = commandReader.getAction();
        if (!availableActionList.contains(chosenAction)) {
            log.warn("The Action: {} is not a valid action!", chosenAction.name());
            return choseAction(availableActionList);
        }
        return attachCommandProcess(chosenAction);
    }

    private ActionCommand attachCommandProcess(ActionCommand chosenAction) {
        if (chosenAction != ActionCommand.ATTACK) {
            if (chosenAction == ActionCommand.MOVE_UP) {
                this.direction = CharacterDirection.UP;
            } else if (chosenAction == ActionCommand.MOVE_DOWN) {
                this.direction = CharacterDirection.DOWN;
            } else if (chosenAction == ActionCommand.MOVE_LEFT) {
                this.direction = CharacterDirection.LEFT;
            } else if (chosenAction == ActionCommand.MOVE_RIGHT) {
                this.direction = CharacterDirection.RIGHT;
            }
            this.setSymbol(this.direction.getDirectionSymbol());
        }
        return chosenAction;
    }
}
