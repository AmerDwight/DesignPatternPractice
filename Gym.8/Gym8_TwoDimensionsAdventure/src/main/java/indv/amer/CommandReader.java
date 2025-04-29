package indv.amer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
@AllArgsConstructor
public class CommandReader {

    List<String> ACCEPT_COMMANDS_IN_UPPERCASE;

    public String getInput() {
        String input;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
            if (ACCEPT_COMMANDS_IN_UPPERCASE.contains(input.toUpperCase())) {
                break;
            } else {
                log.info("Please give a legal command, such as: {}", ACCEPT_COMMANDS_IN_UPPERCASE);
            }
        }
        return input;
    }
}
