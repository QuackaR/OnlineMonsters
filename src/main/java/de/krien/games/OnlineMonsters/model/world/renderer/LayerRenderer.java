package de.krien.games.OnlineMonsters.model.world.renderer;

import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.view.texture.TextureUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class LayerRenderer {

    private Texture tiles;
    private Vector2i[][] data;

    private int coordinatesID = GL15.glGenBuffers();
    private int texturesID = GL15.glGenBuffers();

    public LayerRenderer(Texture tiles, Vector2i[][] data) {
        this.tiles = tiles;
        this.data = data;
    }

    public void draw() {
        Color.white.bind();
        tiles.bind();

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

        GL11.glPushMatrix();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, coordinatesID);
        GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texturesID);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0L);
        GL11.glDrawArrays(GL11.GL_QUADS, 0, 32768);
        GL11.glPopMatrix();
    }

    public void render() {
        List<Float> coordinates = calculateCoordinates();
        List<Float> textures = calculateTextures();
        FloatBuffer groundCoordinatesData = generateBuffer(coordinates);
        FloatBuffer groundTexturesData = generateBuffer(textures);
        bindDataToBuffers(groundCoordinatesData, groundTexturesData);
    }

    private List<Float> calculateCoordinates() {
        List<Float> coordinates = new ArrayList<Float>();
        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                if(data[x][y] != null) {
                    int leftCoordinates = x * TextureUtil.TILE_SIZE;
                    int rightCoordinates = leftCoordinates + TextureUtil.TILE_SIZE;
                    int upperCoordinates = y * TextureUtil.TILE_SIZE;
                    int lowerCoordinates = upperCoordinates + TextureUtil.TILE_SIZE;

                    //Upper Left
                    coordinates.add(Float.valueOf(leftCoordinates));
                    coordinates.add(Float.valueOf(upperCoordinates));
                    //Upper Right
                    coordinates.add(Float.valueOf(rightCoordinates));
                    coordinates.add(Float.valueOf(upperCoordinates));
                    //Lower Right
                    coordinates.add(Float.valueOf(rightCoordinates));
                    coordinates.add(Float.valueOf(lowerCoordinates));
                    //Lower Left
                    coordinates.add(Float.valueOf(leftCoordinates));
                    coordinates.add(Float.valueOf(lowerCoordinates));
                }
            }
        }
        return coordinates;
    }

    private List<Float> calculateTextures() {
        List<Float> textures = new ArrayList<Float>();
        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                if(data[x][y] != null) {
                    float tileDim = 1.00f / 21.35f;
                    float leftTexture = data[x][y].getX() * tileDim;
                    float rightTexture = leftTexture + tileDim - 1.0f / tiles.getImageWidth();
                    float upperTexture = data[x][y].getY() * tileDim;
                    float lowerTexture = upperTexture + tileDim  - 1.0f / tiles.getImageWidth();

                    //Upper Left
                    textures.add(Float.valueOf(leftTexture));
                    textures.add(Float.valueOf(upperTexture));
                    //Upper Right
                    textures.add(Float.valueOf(rightTexture));
                    textures.add(Float.valueOf(upperTexture));
                    //Lower Right
                    textures.add(Float.valueOf(rightTexture));
                    textures.add(Float.valueOf(lowerTexture));
                    //Lower Left
                    textures.add(Float.valueOf(leftTexture));
                    textures.add(Float.valueOf(lowerTexture));
                }
            }
        }
        return textures;
    }

    private void bindDataToBuffers(FloatBuffer groundCoordinatesData, FloatBuffer groundTexturesData) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, coordinatesID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, groundCoordinatesData, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texturesID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, groundTexturesData, GL15.GL_STATIC_DRAW);
    }

    private FloatBuffer generateBuffer(List<Float> data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.size());
        for(Float entry : data) {
            buffer.put(entry);
        }
        buffer.flip();
        return buffer;
    }

    public Vector2i[][] getData() {
        return data;
    }
}
