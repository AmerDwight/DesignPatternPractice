package game.component;

import game.CardGameTemplate;
import game.component.card.uno.UnoCard;
import game.component.constraint.UnoShowConstraint;
import game.component.context.UnoContext;
import game.component.player.Player;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class UnoGame extends CardGameTemplate<UnoCard> {
    final int PLAYER_NUMBER = 4 ;
    final int PLAYER_HAND_INIT_SIZE = 5 ;

    public UnoGame() {
        this.gameContext = new UnoContext(PLAYER_NUMBER, PLAYER_HAND_INIT_SIZE);
        this.showConstraint = new UnoShowConstraint();
    }

    @Override
    public void doPlayerAction(Player<UnoCard> player) {
        // Generally
        List<UnoCard> availableHands = this.showConstraint.judgement(player, this.gameContext);
        if (CollectionUtils.isNotEmpty(availableHands)) {
            this.gameContext.updateStatus(player, player.show(availableHands));
        } else {
            player.draw(this.gameContext.getDeck());
            this.gameContext.updateStatus(player, null);
        }

    }

    @Override
    protected Player<UnoCard> checkWinnerAfterEachTurn() {
        if (CollectionUtils.isEmpty(this.getLastTurnPlayer().getHand())) {
            return this.getLastTurnPlayer();
        } else {
            checkDeck();
        }
        return null;
    }

    private void checkDeck() {
        System.out.println("check Deck size: " + this.gameContext.getDeck().getCardStack().size());
        System.out.println("check Graveyard size: " + this.gameContext.getGraveyard().getCardStack().size());
        if (CollectionUtils.isEmpty(this.gameContext.getDeck().getCardStack())) {
            System.out.println("deck is empty! re-init from graveyard");
            this.gameContext.getDeck().importFrom(
                    this.gameContext.getGraveyard().withDrawAll()
            );
        }
    }
}
