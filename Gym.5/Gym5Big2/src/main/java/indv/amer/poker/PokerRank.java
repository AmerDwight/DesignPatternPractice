package indv.amer.poker;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum PokerRank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String rank;

    PokerRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank;
    }

    public static PokerRank readRank(String rankStr){
        if(StringUtils.isBlank(rankStr)){
            throw new IllegalArgumentException("Empty RankString.");
        }
        if(StringUtils.equalsIgnoreCase(rankStr,ACE.getRank())){
            return ACE;
        }else if(StringUtils.equalsIgnoreCase(rankStr,TWO.getRank())){
            return TWO;
        }else if(StringUtils.equalsIgnoreCase(rankStr,THREE.getRank())){
            return THREE;
        }else if(StringUtils.equalsIgnoreCase(rankStr,FOUR.getRank())){
            return FOUR;
        }else if(StringUtils.equalsIgnoreCase(rankStr,FIVE.getRank())){
            return FIVE;
        }else if(StringUtils.equalsIgnoreCase(rankStr,SIX.getRank())){
            return SIX;
        }else if(StringUtils.equalsIgnoreCase(rankStr,SEVEN.getRank())){
            return SEVEN;
        }else if(StringUtils.equalsIgnoreCase(rankStr,EIGHT.getRank())){
            return EIGHT;
        }else if(StringUtils.equalsIgnoreCase(rankStr,NINE.getRank())){
            return NINE;
        }else if(StringUtils.equalsIgnoreCase(rankStr,TEN.getRank())){
            return TEN;
        }else if(StringUtils.equalsIgnoreCase(rankStr,JACK.getRank())){
            return JACK;
        }else if(StringUtils.equalsIgnoreCase(rankStr,QUEEN.getRank())){
            return QUEEN;
        }else if(StringUtils.equalsIgnoreCase(rankStr,KING.getRank())){
            return KING;
        }else{
            throw new IllegalArgumentException("Unknown Suit Symbol Cannot recognize.");
        }
    }
}