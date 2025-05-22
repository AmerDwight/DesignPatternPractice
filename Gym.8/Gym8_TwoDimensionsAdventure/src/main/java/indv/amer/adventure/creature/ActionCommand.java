package indv.amer.adventure.creature;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public enum ActionCommand {
    MOVE_UP,
    MOVE_DOWN,
    MOVE_LEFT,
    MOVE_RIGHT,
    ATTACK;

    public static List<ActionCommand> getCommandList() {
        return Arrays.asList(
                MOVE_UP,
                MOVE_DOWN,
                MOVE_LEFT,
                MOVE_RIGHT,
                ATTACK
        );
    }

    public static ActionCommand getCommandByName(String name) {
        for (ActionCommand actionCommand : getCommandList()) {
            if (StringUtils.equalsIgnoreCase(actionCommand.name(), name)) {
                return actionCommand;
            }
        }
        return null;
    }
}
