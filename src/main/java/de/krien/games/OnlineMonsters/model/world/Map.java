package de.krien.games.OnlineMonsters.model.world;

import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.view.texture.TextureUtil;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private Texture groundTiles;
    private Texture blockingTiles;

    private Vector2i[][] groundData;
    private Vector2i[][] blockingData;

    public void init() {
        groundTiles = TextureUtil.getGroundTiles();
        blockingTiles = TextureUtil.getBlockingTiles();

        groundData = new Vector2i[64][64];
        for(int x = 0; x < groundData.length; x++) {
            for (int y = 0; y < groundData[x].length; y++) {
                groundData[x][y] = new Vector2i(0, 0);
            }
        }
        groundData[2][2] = new Vector2i(4, 0);
        groundData[3][2] = new Vector2i(5, 0);
        groundData[4][2] = new Vector2i(6, 0);

        groundData[2][3] = new Vector2i(4, 1);
        groundData[3][3] = new Vector2i(5, 1);
        groundData[4][3] = new Vector2i(6, 1);

        groundData[2][4] = new Vector2i(4, 2);
        groundData[3][4] = new Vector2i(5, 2);
        groundData[4][4] = new Vector2i(6, 2);
    }

    public void draw() {
        Color.white.bind();
        groundTiles.bind();

        //List<Float> coords = new ArrayList<Float>();
        //List<Float> textures = new ArrayList<Float>();

        GL11.glBegin(GL11.GL_QUADS);
        for(int x = 0; x < groundData.length; x++) {
            for(int y = 0; y < groundData[x].length; y++) {
                int leftCoords = x * TextureUtil.TILE_SIZE;
                int rightCoords = leftCoords + TextureUtil.TILE_SIZE;
                int upperCoords = y * TextureUtil.TILE_SIZE;
                int lowerCoords = upperCoords + TextureUtil.TILE_SIZE;

                float tileDim = 1.00f / 21.35f;
                float leftTexture = groundData[x][y].getX() * tileDim;
                float rightTexture = leftTexture + tileDim - 1.0f / groundTiles.getImageWidth();
                float upperTexture = groundData[x][y].getY() * tileDim;
                float lowerTexture = upperTexture + tileDim  - 1.0f / groundTiles.getImageWidth();

                //Upper Left
                GL11.glTexCoord2f(leftTexture, upperTexture);
                GL11.glVertex2f(leftCoords, upperCoords);
                //Upper Right
                GL11.glTexCoord2f(rightTexture, upperTexture);
                GL11.glVertex2f(rightCoords, upperCoords);
                //Lower Right
                GL11.glTexCoord2f(rightTexture, lowerTexture);
                GL11.glVertex2f(rightCoords, lowerCoords);
                //Lower Left
                GL11.glTexCoord2f(leftTexture, lowerTexture);
                GL11.glVertex2f(leftCoords, lowerCoords);
            }
        }
        GL11.glEnd();
    }

    public void update() {

    }
}
