package indv.amer.creature.character;

import lombok.Getter;

import java.util.Random;

@Getter
public enum CharacterDirection {
    LEFT(""),
    RIGHT(""),
    UP(""),
    DOWN("");

    final String directionSymbol;

    CharacterDirection(String _directionSymbol) {
        this.directionSymbol = _directionSymbol;
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
