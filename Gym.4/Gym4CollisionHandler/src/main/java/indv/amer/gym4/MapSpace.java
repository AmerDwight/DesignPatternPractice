package indv.amer.gym4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import indv.amer.gym4.sprite.Sprite;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapSpace {
    Integer estateNumber;
    Sprite sprite;
}
