package indv.amer;

import indv.amer.creature.Creature;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
public class AdventureMap {
    private int length;
    private int width;

    // Set up double map for efficiently look up map object.
    @Getter
    private Map<Integer, Map<Integer, MapObject>> map;

    public AdventureMap(int _length, int _width) {
        this.length = _length;
        this.width = _width;
        this.map = initEmptyMap(this.length);
    }

    public void init(@NonNull Character character, int monsterCount, int treasureCount) {
        // Verify
        if (monsterCount + treasureCount + 1 > this.length * this.width) {
            log.info("Too many things inside the map, please retry.");
        }
        if (monsterCount <= 0) {

        }

    }

    private Map<Integer, Map<Integer, MapObject>> initEmptyMap(int mapLength) {
        Map<Integer, Map<Integer, MapObject>> newEmptyMap = new HashMap<>();
        IntStream.range(0, mapLength).forEach(
                i -> {
                    newEmptyMap.put(i, new HashMap<>());
                }
        );
        return newEmptyMap;
    }

    public boolean isPositionEmpty(@NonNull MapPosition mapPosition) {
        return null == this.map.get(mapPosition.getDimensionX()).get(mapPosition.getDimensionY());
    }

    public List<MapPosition> getEmptyPositions() {
        List<MapPosition> availablePositions = new ArrayList<>();
        for (int x = 0; x < this.length; x++) {
            for (int y = 0; y < this.width; y++) {
                if (map.get(x).get(y) == null) {
                    availablePositions.add(new MapPosition(x, y));
                }
            }
        }
        return availablePositions;
    }

    public void moveObject(MapObject mapObject, MapPosition toPosition) {
        if (this.isPositionEmpty(toPosition)) {
            this.eliminateMapObject(mapObject);
            this.map.get(toPosition.getDimensionX()).put(toPosition.getDimensionY(), mapObject);
            mapObject.setPosition(toPosition);
        } else {
            log.warn("Move failed, destination is not empty.");
        }
    }

    public void eliminateMapObject(MapObject mapObject) {
        if (mapObject != null) {
            MapPosition position = mapObject.getPosition();
            this.map.get(position.getDimensionX()).put(position.getDimensionY(), null);
        }
    }

    public List<Creature> getExistCreatures() {
        return this.map
                .values().stream()
                .flatMap(m -> m.values().stream())
                .filter(obj -> obj instanceof Creature)
                .map(obj -> (Creature) obj)
                .toList();
    }

}
