import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sprite.Sprite;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapSpace {
    Integer estateNumber;
    Sprite sprite;

    public void purgeSpace() {
        this.sprite = null;
    }

    public void putSprite(Sprite newSprite) {
        this.sprite = newSprite;
    }

}
