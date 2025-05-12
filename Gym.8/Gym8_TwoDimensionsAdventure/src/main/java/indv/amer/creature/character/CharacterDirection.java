package indv.amer.creature.character;

import java.util.Random;

public enum CharacterDirection {
    LEFT,
    RIGHT,
    UP,
    DOWN;

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
