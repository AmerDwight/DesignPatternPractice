package indv.amer.adventure;

import indv.amer.adventure.creature.ActionCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class CommandReader {

    List<ActionCommand> ACCEPT_COMMANDS = ActionCommand.getCommandList();

    public ActionCommand getAction() {
        String input;
        List<String> acceptedCommandInUpperCase = this.ACCEPT_COMMANDS.stream()
                .map(ActionCommand::name)
                .map(String::toUpperCase).toList();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
            if (acceptedCommandInUpperCase.contains(input.toUpperCase())) {
                return ActionCommand.getCommandByName(input);
            } else {
                ActionCommand actionCommand = shortCut(input);
                if (actionCommand != null) {
                    return actionCommand;
                } else {
                    log.info("Please give a legal command, such as: {}", acceptedCommandInUpperCase);
                }
            }
        }
    }

    private ActionCommand shortCut(String input) {
        String upperInput = input.toUpperCase();
        if (upperInput.contains("U")) {
            return ActionCommand.MOVE_UP;
        } else if (upperInput.contains("D")) {
            return ActionCommand.MOVE_DOWN;
        } else if (upperInput.contains("L")) {
            return ActionCommand.MOVE_LEFT;
        } else if (upperInput.contains("R")) {
            return ActionCommand.MOVE_RIGHT;
        } else if (upperInput.contains("A")) {
            return ActionCommand.ATTACK;
        }
        return null;
    }
}
