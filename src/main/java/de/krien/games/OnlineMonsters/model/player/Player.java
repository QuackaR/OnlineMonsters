package de.krien.games.OnlineMonsters.model.player;


import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.view.texture.TextureUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Player {

    private Texture playerImage;
    private Vector2i position;

    public void init(int modelID) {
        playerImage = TextureUtil.loadPlayerImage(modelID);
        position = new Vector2i();
    }

    public void draw() {
        Color.white.bind();
        playerImage.bind();

        int offsetX = position.getX() * 48;
        int offsetY = position.getY() * 48;

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0,0);
        GL11.glVertex2f(0 + offsetX, 0 + offsetY);
        GL11.glTexCoord2f(1,0);
        GL11.glVertex2f(playerImage.getTextureWidth() + offsetX , 0 + offsetY);
        GL11.glTexCoord2f(1,1);
        GL11.glVertex2f(playerImage.getTextureWidth() + offsetX, playerImage.getTextureHeight() + offsetY);
        GL11.glTexCoord2f(0,1);
        GL11.glVertex2f(0 + offsetX, playerImage.getTextureHeight() + offsetY);
        GL11.glEnd();
    }

    public void update() {
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            position.setY(position.getY() + 1);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            position.setY(position.getY() - 1);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            position.setX(position.getX() + 1);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            position.setX(position.getX() - 1);
        }
    }
}
