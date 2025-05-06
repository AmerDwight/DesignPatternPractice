package indv.amer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class MapObject {
    private String Symbols;
    private MapPosition position;
}
