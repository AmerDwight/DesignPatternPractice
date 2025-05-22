package indv.amer.adventure.map;

import indv.amer.adventure.creature.Creature;
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
            this.map.get(destination.getDimensionX()).put(destination.getDimensionY(),mapObject);
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
        List<StringBuilder> mapBuilder = new ArrayList<>();
        IntStream.range(0, this.width).forEach(
                i -> {
                    mapBuilder.add(new StringBuilder());
                }
        );
        log.info("------------------------------------------");
        for (int i = 0; i < this.length; i++) {
            for(int j = 0 ; j < this.width; j++){
                MapObject obj = map.get(i).get(j);
                if(obj == null){
                    mapBuilder.get(j).append("　");
                }else{
                    mapBuilder.get(j).append(obj.getSymbol());
                }
            }
        }
        for(StringBuilder sb : mapBuilder){
            log.info(sb.toString());
        }
        log.info("------------------------------------------");

    }

}
