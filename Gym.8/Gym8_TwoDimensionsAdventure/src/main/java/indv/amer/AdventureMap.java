package indv.amer;

import indv.amer.creature.Creature;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Data
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

    public MapPosition getRandomEmptyPosition() {
        List<MapPosition> emptyPositions = getEmptyPositions();
        if (CollectionUtils.isNotEmpty(emptyPositions)) {
            Collections.shuffle(emptyPositions);
            return emptyPositions.get(0);
        } else {
            log.warn("No empty map space left.");
            return null;
        }
    }

    public void randomlyPutObject(@NonNull MapObject mapObject) {
        List<MapPosition> emptyPositions = getEmptyPositions();
        if (CollectionUtils.isNotEmpty(emptyPositions)) {
            Collections.shuffle(emptyPositions);
            MapPosition destination = emptyPositions.get(0);
            mapObject.setPosition(destination);
            this.map.get(destination.getDimensionX()).get(destination.getDimensionY());
        } else {
            log.warn("No empty map space left.");
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

    public MapObject getMapObjectByPosition(MapPosition mapPosition) {
        if (MapPosition.isValidPosition(mapPosition, this)) {
            return this.map.get(mapPosition.getDimensionX()).get(mapPosition.getDimensionY());
        }
        return null;
    }

    public void printMap() {
        // Edge case: If map is not initialized
        if (map == null) {
            System.out.println("Map is not initialized.");
            return;
        }

        // Find the actual map boundaries based on objects
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        // Scan for actual boundaries by looking at all map objects
        for (Integer x : map.keySet()) {
            Map<Integer, MapObject> yMap = map.get(x);
            if (yMap == null || yMap.isEmpty()) continue;

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);

            for (Integer y : yMap.keySet()) {
                if (yMap.get(y) != null) {
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        // If no objects found in the map
        if (minX == Integer.MAX_VALUE || minY == Integer.MAX_VALUE) {
            System.out.println("Map is empty.");
            return;
        }

        // Use the class properties if they are valid, otherwise use the detected boundaries
        int startX = 0;
        int endX = length > 0 ? length - 1 : maxX;
        int startY = 0;
        int endY = width > 0 ? width - 1 : maxY;

        // Print the top border (with column numbers for reference)
        System.out.print("   ");
        for (int x = startX; x <= endX; x++) {
            System.out.print(x % 10);  // Only print last digit to save space
        }
        System.out.println();

        System.out.print("   ");
        for (int x = startX; x <= endX; x++) {
            System.out.print("-");
        }
        System.out.println();

        // Print map content
        for (int y = startY; y <= endY; y++) {
            // Print row number for reference
            System.out.printf("%2d|", y);

            // Print each cell in the row
            for (int x = startX; x <= endX; x++) {
                MapObject obj = null;

                // Check if this coordinate has a map object
                if (map.containsKey(x) && map.get(x) != null && map.get(x).containsKey(y)) {
                    obj = map.get(x).get(y);
                }

                // Print the symbol or an empty space
                if (obj != null) {
                    System.out.print(obj.getSymbol());
                } else {
                    System.out.print(" ");  // Empty space for null objects
                }
            }
            System.out.println();
        }

        // Print bottom border
        System.out.print("   ");
        for (int x = startX; x <= endX; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
