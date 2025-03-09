import game.component.ShowDownGame;
import game.component.UnoGame;
import game.component.player.AiPlayer;
import game.component.player.HumanPlayer;

import java.util.List;

public class Application {
    public static void main(String[] args){
        ShowDownGame showDownGame = new ShowDownGame();
        showDownGame.gameStart(List.of(
                new AiPlayer<>(),
                new AiPlayer<>(),
                new AiPlayer<>(),
                new AiPlayer<>()
        ));

        UnoGame unoGame = new UnoGame();
        unoGame.gameStart(List.of(
                new AiPlayer<>(),
                new AiPlayer<>(),
                new AiPlayer<>(),
                new AiPlayer<>()
        ));
    }
}
