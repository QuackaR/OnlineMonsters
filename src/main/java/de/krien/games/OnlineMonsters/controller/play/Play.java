package de.krien.games.OnlineMonsters.controller.play;

import de.krien.games.OnlineMonsters.controller.core.IGameState;
import de.krien.games.OnlineMonsters.model.player.Player;
import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.model.world.Chunk;

public class Play implements IGameState {

    Chunk chunk;
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
            chunk = new Chunk();
            chunk.init(new Vector2i(1, 1));
        }
        chunk.draw();
        if(player == null) {
            player = new Player();
            player.init(1);
        }
        player.draw();
    }
}
