package indv.amer;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class AdventureMap {
    private int length;
    private int width;
    private Map<MapPosition, MapObject> map;

    public AdventureMap(int _length, int _width) {
        this.length = _length;
        this.width = _width;
    }

    public void init(@NonNull Character character, int monsterCount, int treasureCount) {
        // Verify
        if (monsterCount + treasureCount + 1 > this.length * this.width) {
            log.info("Too many things inside the map, please retry.");
        }
        if (monsterCount <= 0) {

        }

    }

}
