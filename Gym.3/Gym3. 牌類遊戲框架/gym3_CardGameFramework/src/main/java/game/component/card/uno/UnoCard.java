package game.component.card.uno;

import game.component.card.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnoCard implements Card {
    private final UnoCardColor color;
    private final UnoCardNumber number;

}
