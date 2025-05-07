package indv.amer.creature;

import java.util.Arrays;
import java.util.List;

public enum ActionCommand {
    MOVE_UP,
    MOVE_DOWN,
    MOVE_LEFT,
    MOVE_RIGHT,
    ATTACK;

    public List<String> getCommandList() {
        return Arrays.asList(
                MOVE_UP.name(),
                MOVE_DOWN.name(),
                MOVE_LEFT.name(),
                MOVE_RIGHT.name(),
                ATTACK.name()
        );
    }
}
