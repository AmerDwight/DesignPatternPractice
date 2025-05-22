package indv.amer;

import indv.amer.adventure.Game;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(10,10,10,10);
        game.start();
    }
}
