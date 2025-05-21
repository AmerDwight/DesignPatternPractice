package indv.amer.creature.character;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

@Getter
public enum CharacterDirection {
    LEFT("←"),
    RIGHT("→"),
    UP("↑"),
    DOWN("↓");

    final String directionSymbol;

    CharacterDirection(String _directionSymbol) {
        this.directionSymbol = _directionSymbol;
    }

    public static CharacterDirection getBySymbol(String symbol) {
        for (CharacterDirection direction : CharacterDirection.values()) {
            if (StringUtils.equalsIgnoreCase(symbol, direction.getDirectionSymbol())) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Not exists such symbol: " + symbol);
    }

    public static CharacterDirection getRandomDirection() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        if (randomDouble > 0.75) {
            return LEFT;
        } else if (randomDouble > 0.5) {
            return RIGHT;
        } else if (randomDouble > 0.25) {
            return UP;
        } else {
            return DOWN;
        }
    }
}
