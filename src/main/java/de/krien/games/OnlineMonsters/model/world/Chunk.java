package de.krien.games.OnlineMonsters.model.world;

import de.krien.games.OnlineMonsters.view.texture.TextureUtil;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;

public class Chunk {

    private final static int TILE_SIZE = 48;
    private final static int TILE_COUNT = 64;

    private Texture chunkImage;
    private boolean[][] blockedTiles;

    public void init(Vector2i coords) {
        chunkImage = TextureUtil.loadChunkImage(coords);
    }

    public void draw() {
        Color.white.bind();
        chunkImage.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0,0);
        GL11.glVertex2f(0,0);
        GL11.glTexCoord2f(1,0);
        GL11.glVertex2f(chunkImage.getTextureWidth(),0);
        GL11.glTexCoord2f(1,1);
        GL11.glVertex2f(chunkImage.getTextureWidth(),chunkImage.getTextureHeight());
        GL11.glTexCoord2f(0,1);
        GL11.glVertex2f(0,chunkImage.getTextureHeight());
        GL11.glEnd();
    }
}
