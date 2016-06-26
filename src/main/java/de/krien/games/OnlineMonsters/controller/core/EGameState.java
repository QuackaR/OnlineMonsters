package de.krien.games.OnlineMonsters.controller.core;

import de.krien.games.OnlineMonsters.controller.menu.Menu;

public enum EGameState {

    MENU(new Menu()),
    PLAY(null),
    EXIT(null);

    private IGameState reference;

    EGameState(IGameState reference) {
        this.reference = reference;
    }

    public void update() {
        reference.update();
    }

    public void draw() {
        reference.draw();
    }

    public IGameState getReference() {
        return reference;
    }
}
