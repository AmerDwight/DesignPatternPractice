package game.component.card.uno;

import lombok.Getter;

@Getter
public enum UnoCardColor {
    BLUE("BLUE"),
    RED("RED"),
    YELLOW("YELLOW"),
    GREEN("GREEN");

    private final String color;

    UnoCardColor(String _color) {
        this.color = _color;
    }
}
