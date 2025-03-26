package indv.amer.poker;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum PokerSuit {
    SPADE("S"),
    HEART("H"),
    DIAMOND("D"),
    CLUB("C");

    private final String symbol;

    PokerSuit(String _symbol) {
        this.symbol = _symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    public static PokerSuit readSuitSymbol(String suitSymbol){
        if(StringUtils.isBlank(suitSymbol)){
            throw new IllegalArgumentException("Empty SuitSymbol.");
        }
        if(StringUtils.equalsIgnoreCase(suitSymbol,SPADE.getSymbol())){
            return SPADE;
        } else if (StringUtils.equalsIgnoreCase(suitSymbol,HEART.getSymbol())) {
            return HEART;
        } else if (StringUtils.equalsIgnoreCase(suitSymbol,DIAMOND.getSymbol())) {
            return DIAMOND;
        } else if (StringUtils.equalsIgnoreCase(suitSymbol,CLUB.getSymbol())) {
            return CLUB;
        }else{
            throw new IllegalArgumentException("Unknown Suit Symbol Cannot recognize.");
        }
    }

}
