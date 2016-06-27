package de.krien.games.OnlineMonsters.view.texture;

import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextureUtil {

    private final static String MAP_PATH = "res/map/";
    private final static String PLAYER_PATH = "res/player/";
    private final static String IMAGE_TYPE = "png";

    public static Texture loadChunkImage(Vector2i coords) {
        String fileName = "MAP_" + coords.getX() + "_" + coords.getY() + "." + IMAGE_TYPE;
        try {
            return TextureLoader.getTexture(IMAGE_TYPE.toUpperCase(), new FileInputStream(new File(MAP_PATH + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Texture loadPlayerImage(int modelID) {
        String fileName = "PLAYER_" + modelID + "." + IMAGE_TYPE;
        try {
            return TextureLoader.getTexture(IMAGE_TYPE.toUpperCase(), new FileInputStream(new File(PLAYER_PATH + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
