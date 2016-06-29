package de.krien.games.OnlineMonsters.controller.play;

import de.krien.games.OnlineMonsters.controller.core.IGameState;
import de.krien.games.OnlineMonsters.model.player.Player;
import de.krien.games.OnlineMonsters.model.world.Map;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Play implements IGameState {

    private Map map;
    private Player player;

    private long timeOfLastGameLoop = System.nanoTime();

    public Play() {
    }

    public void init() {
        player = new Player();
        player.init(1);
        map = new Map();
        map.init();
    }

    @Override
    public void update() {
        float timeSinceLastGameLoop = getTimeSinceLastGameLoop();
        player.update(timeSinceLastGameLoop);
    }

    @Override
    public void draw() {
        map.draw();
        player.draw();
    }

    private float getTimeSinceLastGameLoop() {
        long timeOfThisGameLoop = System.nanoTime();
        long timeBetweenGameLoops = timeOfThisGameLoop - timeOfLastGameLoop;
        timeOfLastGameLoop = timeOfThisGameLoop;
        float secondsSinceLastGameLoop = (float) timeBetweenGameLoops / 1000000000;
        return secondsSinceLastGameLoop;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
