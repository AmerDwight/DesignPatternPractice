package indv.amer;

import indv.amer.creature.ActionCommand;
import lombok.AllArgsConstructor;
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
                log.info("Please give a legal command, such as: {}", acceptedCommandInUpperCase);
            }
        }
    }
}
