import handler.CollisionHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class World {
    private final Integer width;
    private final List<MapSpace> worldMap = new ArrayList<>();
    private final CollisionHandler collisionHandler;

    public World(int _width, CollisionHandler _collisionHandler) {
        this.width = _width;
        this.collisionHandler = _collisionHandler;
    }

    public void start() {
        // TODO
    }

    public void randomInit() {
        // TODO
    }

    private void moveSprite(int fromIndex, int toIndex) {
        if (isPositionUnavailable(fromIndex) || isPositionUnavailable(toIndex)) {
            log.error("Position is illegal.");
        }

    }

    private boolean isPositionUnavailable(int someIndex) {
        return someIndex < 0 || someIndex > this.width - 1;
    }

    private void printRealTimeMap() {
        log.info("\n=========================================================");

        log.info("RealTimeMap:");
        StringBuilder horizon = new StringBuilder("|");
        IntStream.range(0, worldMap.size()).forEach(
                i -> horizon.append("-")
        );
        horizon.append("|");
        StringBuilder spriteMarker = new StringBuilder(" ");
        worldMap.forEach(
                mapSpace -> {
                    if (mapSpace.getSprite() != null) {
                        spriteMarker.append(mapSpace.getSprite().getSymbol());
                    } else {
                        spriteMarker.append(" ");
                    }
                }
        );
        log.info(spriteMarker.toString());
        log.info(horizon.toString());

        log.info("\n=========================================================");
    }
}
