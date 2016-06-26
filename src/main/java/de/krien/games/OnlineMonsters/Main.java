package de.krien.games.OnlineMonsters;

import de.krien.games.OnlineMonsters.controller.core.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }

}
