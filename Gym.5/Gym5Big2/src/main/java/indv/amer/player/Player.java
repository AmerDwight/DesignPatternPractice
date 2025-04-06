package indv.amer.player;

import indv.amer.Validated;
import indv.amer.comparator.Big2BasicComparator;
import indv.amer.poker.PokerCard;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Getter
public class Player {
    // 正則表達式模式定義：允許A-Z、a-z、0-9和中文字
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z0-9\u4e00-\u9fa5]+$");

    String name;
    List<PokerCard> handCards = new ArrayList<>();

    public Player(final String _name) {
        if (isValidName(_name)) {
            this.name = _name;
        } else {
            throw new IllegalArgumentException("Wrong Input For Name");
        }
    }

    public void receiveCard(@NonNull PokerCard card) {
        this.handCards.add(card);
        this.sortingHands();
    }

    public void play(@Validated List<PokerCard> onPlayCards) {
        handCards = new ArrayList<>(CollectionUtils.subtract(handCards, onPlayCards));
        this.sortingHands();
    }

    public void printHandCards() {
        if (CollectionUtils.isEmpty(this.handCards)) {
            throw new IllegalArgumentException("No Cards On Player: " + this.getName());
        } else {

            StringBuilder handCardsIndexStringBuilder = new StringBuilder();
            StringBuilder handCardsStringBuilder = new StringBuilder();
            IntStream.range(0, this.handCards.size()).forEach(
                    i -> {
                        PokerCard card = this.handCards.get(i);
                        String cardString = card.toString();
                        if (!handCardsStringBuilder.isEmpty()) {
                            handCardsStringBuilder.append(" ");
                            handCardsIndexStringBuilder.append(" ");
                        }
                        handCardsIndexStringBuilder.append(i);
                        handCardsStringBuilder.append(cardString);

                        // Index列 後方補空白
                        if(i !=this.handCards.size()-1){
                            IntStream.range(0, handCardsStringBuilder.length() - handCardsIndexStringBuilder.length()).forEach(
                                    spaceNumber -> {
                                        handCardsIndexStringBuilder.append(" ");
                                    }
                            );
                        }
                    }
            );
            log.info(handCardsIndexStringBuilder.toString());
            log.info(handCardsStringBuilder.toString());
        }
    }

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return NAME_PATTERN.matcher(name).matches();
    }
    private void sortingHands(){
        if(CollectionUtils.isNotEmpty(this.getHandCards())){
            this.handCards = this.getHandCards().stream().sorted(Big2BasicComparator.big2Comparator()).collect(Collectors.toCollection(ArrayList::new));
        }
    }
}
