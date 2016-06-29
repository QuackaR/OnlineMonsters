package de.krien.games.OnlineMonsters.view.texture;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextureUtil {

    private final static String MAP_PATH = "res/map/";
    private final static String MAP_GROUND_TILES = "Ground_Tiles.png";
    private final static String MAP_BLOCKING_TILES = "Blocking_Tiles.png";

    private final static String PLAYER_PATH = "res/player/";
    private final static String IMAGE_TYPE = "PNG";

    public final static int TILE_SIZE = 48;
    public final static int TILE_COUNT = 16;

    private static Texture groundTiles;
    private static Texture blockingTiles;

    public static Texture getGroundTiles() {
        if(groundTiles == null) {
            groundTiles = loadTiles(MAP_GROUND_TILES);
        }
        return groundTiles;
    }

    public static Texture getBlockingTiles() {
        if(blockingTiles == null) {
            blockingTiles = loadTiles(MAP_BLOCKING_TILES);
        }
        return blockingTiles;
    }

    private static Texture loadTiles(String fileName) {
        try {
            return TextureLoader.getTexture(IMAGE_TYPE, new FileInputStream(new File(MAP_PATH + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Texture loadPlayerImage(int modelID) {
        String fileName = "PLAYER_" + modelID + "." + IMAGE_TYPE.toLowerCase();
        try {
            return TextureLoader.getTexture(IMAGE_TYPE, new FileInputStream(new File(PLAYER_PATH + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
