package game.component.player;

import java.util.List;
import java.util.Random;

/**
 * In usages of Ai Player.
 */
public class RandomPlayerNameService {
    private static final List<String> NAMES = List.of(
            // Male Names
            "Michael", "John", "David", "James", "Robert",
            "William", "Joseph", "Christopher", "Daniel", "Matthew",

            // Female Names
            "Emily", "Emma", "Elizabeth", "Jennifer", "Linda",
            "Sarah", "Jessica", "Amanda", "Ashley", "Lauren"
    );

    private static final Random random = new Random();

    public static String generateRandomName() {
        return NAMES.get(random.nextInt(NAMES.size()));
    }
}