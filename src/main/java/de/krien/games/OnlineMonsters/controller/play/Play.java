package de.krien.games.OnlineMonsters.controller.play;

import de.krien.games.OnlineMonsters.controller.core.IGameState;
import de.krien.games.OnlineMonsters.model.player.Player;
import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.model.world.Map;

public class Play implements IGameState {

    Map chunk;
    Player player;

    public Play() {

    }

    @Override
    public void update() {
        if(player == null) {
            player = new Player();
            player.init(1);
        }
        player.update();
    }

    @Override
    public void draw() {
        if(chunk == null) {
            chunk = new Map();
            chunk.init();
        }
        chunk.draw();
        if(player == null) {
            player = new Player();
            player.init(1);
        }
        player.draw();
    }
}
