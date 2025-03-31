package indv.amer.player;

import indv.amer.poker.PokerCard;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class Player {
    @Getter
    @Setter
    String name;
    List<PokerCard> handCards = new ArrayList<>();

    public void withdraw(@NonNull PokerCard card) {
        this.handCards.add(card);
    }

    public List<PokerCard> preparePlay(String command) {
        // TODO
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
                        IntStream.range(0, handCardsStringBuilder.length() - handCardsIndexStringBuilder.length()).forEach(
                                spaceNumber -> {
                                    handCardsIndexStringBuilder.append(" ");
                                }
                        );
                    }
            );
            log.info(handCardsIndexStringBuilder.toString());
            log.info(handCardsStringBuilder.toString());
        }
    }

}
