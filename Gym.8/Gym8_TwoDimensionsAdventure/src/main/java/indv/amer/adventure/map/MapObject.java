package indv.amer.adventure.map;

import lombok.Data;

@Data
public abstract class MapObject {
    private String symbol;
    private MapPosition position;

    public MapObject(String _symbol) {
        this.symbol = _symbol;
    }
}
