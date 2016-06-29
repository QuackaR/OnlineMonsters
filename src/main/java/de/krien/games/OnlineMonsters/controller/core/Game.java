package de.krien.games.OnlineMonsters.controller.core;

import de.krien.games.OnlineMonsters.controller.play.Play;
import de.krien.games.OnlineMonsters.view.display.Window;
import org.lwjgl.opengl.Display;

public class Game {

    private EGameState gameState;
    private Window window;

    public Game() {
        gameState = EGameState.PLAY;
    }

    public void init() {
        window = new Window();
        ((Play) EGameState.PLAY.getReference()).init();
    }

    public void run() {
        while (!Display.isCloseRequested()) {
            update();
            window.clearDisplay();
            draw();
            window.updateDisplay();
        }
        Display.destroy();
    }

    private void update() {
        gameState.update();
    }

    private void draw() {
        gameState.draw();
    }

}
