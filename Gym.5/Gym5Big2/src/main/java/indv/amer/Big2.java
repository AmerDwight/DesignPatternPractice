package indv.amer;

import indv.amer.command.CommandReader;
import indv.amer.comparator.Big2BasicComparator;
import indv.amer.pattern.CardPatternTemplate;
import indv.amer.player.Player;
import indv.amer.poker.PokerCard;
import indv.amer.poker.PokerRank;
import indv.amer.poker.PokerSuit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
        Stack<PokerCard> deck = this.initDeckFromCommand(this.commandReader.getNextCommand());

        // 2 Init Players
        List<Player> players = this.initPlayers(List.of(
                this.commandReader.getNextCommand(),
                this.commandReader.getNextCommand(),
                this.commandReader.getNextCommand(),
                this.commandReader.getNextCommand()));

        // 3 Deal
        this.dealCards(deck, players);

        // 4 get fist player
        int firstPlayerIndex = this.findFirstPlayerIndex(players);

        // 5. Set Break Criteria
        Player winner = null;

        // 5 Start Round
        while (winner == null) {
            // 暫存必要參數
            int passCount = 0;

            Round round = new Round(firstPlayerIndex);
            log.info("新的回合開始了。");
            while (passCount < 3) {
                Player nowPlayer = players.get(round.getNowPlayerIndex());

                // 打印手牌資訊
                log.info("輪到{}了", nowPlayer.getName());


                // 回合中，第一位玩家的處理
                if (isFirstTurn(round)) {
                    boolean isValidPlay = false;
                    List<PokerCard> thisTurnPlay;
                    while (!isValidPlay) {
                        nowPlayer.printHandCards();
                        String nextCommander = this.commandReader.getNextCommand();
                        if (isPassCommand(nextCommander)) {
                            log.info("你不能在新的回合中喊 PASS");
                        } else {
                            thisTurnPlay = getPlayerPlayFromCommand(nowPlayer.getHandCards(), nextCommander);
                            CardPatternTemplate thisRoundPattern = this.cardPattern.checkPattern(thisTurnPlay);
                            if (thisRoundPattern != null) {
                                isValidPlay = true;
                                round.setNowPattern(thisRoundPattern);
                                round.setTopPlay(thisTurnPlay);

                                // 打出Player Play 並宣讀
                                nowPlayer.play(thisTurnPlay);
                                announcePlayerPlay(nowPlayer, false, thisRoundPattern.getSignature(), thisTurnPlay);
                            } else {
                                log.info("此牌型不合法，請再嘗試一次。");
                            }
                        }
                    }
                } else {
                    // 回合中，非第一位玩家的處理
                    boolean isValidPlay = false;
                    boolean isPlayerPass = false;
                    List<PokerCard> thisTurnPlay;
                    while (!isValidPlay) {
                        nowPlayer.printHandCards();
                        String nextCommander = this.commandReader.getNextCommand();
                        if (isPassCommand(nextCommander)) {
                            isValidPlay = true;
                            isPlayerPass = true;
                            passCount += 1;

                            announcePlayerPlay(nowPlayer, isPlayerPass, null, null);
                        } else {
                            thisTurnPlay = getPlayerPlayFromCommand(nowPlayer.getHandCards(), nextCommander);
                            isValidPlay = round.getNowPattern().isNextPlayBigger(round.getTopPlay(), thisTurnPlay);
                            if (isValidPlay) {
                                round.setTopPlay(thisTurnPlay);
                                round.setTopPlayerIndex(round.getNowPlayerIndex());

                                // 打出Player Play 並宣讀
                                nowPlayer.play(thisTurnPlay);
                                announcePlayerPlay(nowPlayer, isPlayerPass, round.getNowPattern().getSignature(), thisTurnPlay);
                                passCount = 0;
                            } else {
                                log.info("此牌型不合法，請再嘗試一次。");
                            }
                        }
                    }

                }
                // 檢查 winner condition 並進行移轉至下一位玩家
                if (CollectionUtils.isEmpty(nowPlayer.getHandCards())) {
                    // 當前玩家打完全部的牌，成為勝利者
                    winner = nowPlayer;
                    break;
                } else {
                    int nextPlayerIndex = round.getNowPlayerIndex() + 1;
                    nextPlayerIndex = nextPlayerIndex >= players.size() ? nextPlayerIndex - players.size() : nextPlayerIndex;
                    round.setNowPlayerIndex(nextPlayerIndex);
                }
            }
            // Pass Count = 3，設定下一回合的起始人員
            if (winner == null) {
                firstPlayerIndex = round.getTopPlayerIndex();
            }
        }
        log.info("遊戲結束，遊戲的勝利者為 {}", winner.getName());
    }

    private void announcePlayerPlay(@Validated Player nowPlayer,
                                    boolean isPass,
                                    @Validated String patternName,
                                    @Validated List<PokerCard> playerPlay) {
        if (isPass) {
            log.info("玩家 {} PASS.", nowPlayer.getName());
        } else {
            playerPlay = playerPlay.stream().sorted(Big2BasicComparator.big2Comparator()).toList();
            log.info("玩家 {} 打出了 {} {}", nowPlayer.getName(), patternName, PokerCard.getPokerListString(playerPlay));
        }
    }

    private List<PokerCard> getPlayerPlayFromCommand(List<PokerCard> playerHands, String playCommand) {
        // 確認指令的正確性
        List<Integer> playCardIndexes = Arrays.stream(playCommand.split(" ")).map(Integer::parseInt).toList();
        for (Integer onCheckIndex : playCardIndexes) {
            if (onCheckIndex == null || onCheckIndex < 0 || onCheckIndex >= playerHands.size()) {
                throw new IllegalArgumentException("Command for Player Hands is illegal!");
            }
        }
        // 確認要打出的牌
        List<PokerCard> playerPlay = new ArrayList<>();
        playCardIndexes.forEach(
                i -> playerPlay.add(playerHands.get(i))
        );
        return playerPlay;
    }

    private boolean isPassCommand(String command) {
        return StringUtils.equals(command.trim(), "-1");
    }

    private boolean isFirstTurn(Round nowRound) {
        return nowRound.getNowPattern() == null;
    }

    private Stack<PokerCard> initDeckFromCommand(String deckCommand) {
        Stack<PokerCard> newDeck = new Stack<>();
        if (StringUtils.isNotBlank(deckCommand)) {
            List<String> cardStrings = List.of(deckCommand.split(" "));
            cardStrings.forEach(
                    cardString -> {
                        newDeck.push(PokerCard.readCard(cardString));
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

    private void dealCards(Stack<PokerCard> deck, List<Player> players) {
        if (CollectionUtils.isNotEmpty(deck) && CollectionUtils.isNotEmpty(players)) {
            int deckSize = deck.size();
            for (int i = 0; i < deckSize; i++) {
                int playerIndex = i % players.size();
                players.get(playerIndex).receiveCard(deck.pop());
            }
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
}
