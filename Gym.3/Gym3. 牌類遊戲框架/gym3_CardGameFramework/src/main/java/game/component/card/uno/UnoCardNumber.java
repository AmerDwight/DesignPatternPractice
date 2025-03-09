package game.component.card.uno;


import lombok.Getter;

@Getter
public enum UnoCardNumber {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);


    private final int number;

    UnoCardNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + "";
    }
}