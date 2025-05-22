package indv.amer.adventure.map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapPosition {
    private int dimensionX;
    private int dimensionY;

    public static boolean isValidPosition(MapPosition position, AdventureMap map) {
        return (position.getDimensionX() <= map.getLength() && position.getDimensionX() >= 0) &&
                (position.getDimensionY() <= map.getWidth() && position.getDimensionY() >= 0);
    }
}
