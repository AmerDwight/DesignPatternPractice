package indv.amer;

import indv.amer.command.CommandReader;
import indv.amer.pattern.CardPatternTemplate;
import indv.amer.player.Player;
import indv.amer.poker.PokerCard;
import indv.amer.poker.PokerRank;
import indv.amer.poker.PokerSuit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class Big2 {
    CommandReader commandReader;
    CardPatternTemplate cardPattern;


    public Big2(CommandReader _commandReader, CardPatternTemplate _cardPattern) {
        this.commandReader = _commandReader;
        this.cardPattern = _cardPattern;
    }

    public void start() {
        // 1 Init Deck
        List<PokerCard> deck = this.initDeckFromCommand(this.commandReader.getNextCommand());

        // 2 Init Players
        List<Player> players = this.initPlayers(List.of(
                this.commandReader.getNextCommand(),
                this.commandReader.getNextCommand(),
                this.commandReader.getNextCommand(),
                this.commandReader.getNextCommand()));

        // 3 Deal
        this.dealCards(deck, players);

        // 4 Top Play Set And Find First Player
        List<PokerCard> topPlay;
        int topPlayerIndex = this.findFirstPlayerIndex(players);

        // 5 Start Round
        while (true) {
            // 暫存必要參數
            int nowPlayerIndex = topPlayerIndex;
            int passCount = 0;

            log.info("新的回合開始了。");
            while(passCount<3){
                Player nowPlayer = players.get(nowPlayerIndex);

            }

        }

    }

    private List<PokerCard> initDeckFromCommand(String deckCommand) {
        List<PokerCard> newDeck = new ArrayList<>();
        if (StringUtils.isNotBlank(deckCommand)) {
            List<String> cardStrings = List.of(deckCommand.split(" "));
            cardStrings.forEach(
                    cardString -> {
                        newDeck.add(PokerCard.readCard(cardString));
                    }
            );
        }
        return newDeck;
    }

    private List<Player> initPlayers(List<String> newPlayerCommands) {
        List<Player> newPlayers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(newPlayerCommands)) {
            newPlayerCommands.forEach(
                    playerName -> {
                        newPlayers.add(new Player(playerName));
                    }
            );
        }
        return newPlayers;
    }

    private void dealCards(List<PokerCard> deck, List<Player> players) {
        if (CollectionUtils.isNotEmpty(deck) && CollectionUtils.isNotEmpty(players)) {
            IntStream.range(0, deck.size()).forEach(
                    i -> {
                        int reminder = i % players.size();
                        players.get(reminder).receiveCard(deck.get(i));
                    }
            );
        }
    }

    private int findFirstPlayerIndex(List<Player> players) {
        PokerCard firstPoker = new PokerCard(PokerSuit.CLUB, PokerRank.THREE);
        if (CollectionUtils.isNotEmpty(players)) {
            for (Player player : players) {
                for (PokerCard poker : player.getHandCards()) {
                    if (poker.equals(firstPoker)) {
                        return players.indexOf(player);
                    }
                }
            }
        }
        return 0;
    }

    private List<PokerCard> getPlayByCommand(List<PokerCard> playHandCards, String playCommand){

    }

}
