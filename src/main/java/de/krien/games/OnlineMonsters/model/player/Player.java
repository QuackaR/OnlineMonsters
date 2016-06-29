package de.krien.games.OnlineMonsters.model.player;


import de.krien.games.OnlineMonsters.controller.core.EGameState;
import de.krien.games.OnlineMonsters.controller.play.Play;
import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.view.texture.TextureUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Player {

    private final static float MOVEMENT_DISTANCE_PER_SECOND = 100.0f;

    private Texture playerImage;
    private Vector2f position;

    public void init(int modelID) {
        playerImage = TextureUtil.loadPlayerImage(modelID);
        position = new Vector2f();
    }

    public void draw() {
        Color.white.bind();
        playerImage.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0,0);
        GL11.glVertex2f(0 + position.getX(), 0 + position.getY());
        GL11.glTexCoord2f(1,0);
        GL11.glVertex2f(playerImage.getTextureWidth() + position.getX(), 0 + position.getY());
        GL11.glTexCoord2f(1,1);
        GL11.glVertex2f(playerImage.getTextureWidth() + position.getX(), playerImage.getTextureHeight() + position.getY());
        GL11.glTexCoord2f(0,1);
        GL11.glVertex2f(0 + position.getX(), playerImage.getTextureHeight() + position.getY());
        GL11.glEnd();
    }

    public void update(float timeSinceLastGameLoop) {
        movePlayer(timeSinceLastGameLoop);
    }

    private void movePlayer(float timeSinceLastGameLoop) {
        float movementDistance = MOVEMENT_DISTANCE_PER_SECOND * timeSinceLastGameLoop;
        position = calculateNextPosition(movementDistance);
    }

    private Vector2f calculateNextPosition(float movementDistance) {
        Vector2f nextPosition = getInput(movementDistance);

        boolean xAxisIsBlocked = checkIfBlocked(new Vector2f(nextPosition.getX(), position.getY()));
        boolean yAxisIsBlocked = checkIfBlocked(new Vector2f(position.getX(), nextPosition.getY()));


        if(xAxisIsBlocked) {
            System.out.println("x-Axis is blocked for - NextPositionX: " + nextPosition.getX() + " and PositionY: " + position.getY());
            System.out.println("FieldX: " + (int) (nextPosition.getX() / 48) + " FieldY: " + (int) (position.getY() / 48));

            float nextPositionX = position.getX();
            if(nextPosition.getX() > position.getX()) {
                nextPositionX = position.getX() + (48 - position.getX() % 48) - 0.01f;
            } else {
                nextPositionX = position.getX() - (position.getX() % 48);
            }
            nextPosition.setX(nextPositionX);
        }

        if(yAxisIsBlocked) {
            float nextPositionY = position.getY();
            if(nextPosition.getY() > position.getY()) {
                nextPositionY = position.getY() + (48 - position.getY() % 48)  - 0.01f;
            } else {
                nextPositionY = position.getY() - (position.getY() % 48);
            }
            nextPosition.setY(nextPositionY);
        }

        return nextPosition;
    }

    private Vector2f getInput(float movementDistance) {
        Vector2f nextPosition = new Vector2f(position.getX(), position.getY());
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            nextPosition.setY(nextPosition.getY() + movementDistance);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            nextPosition.setY(nextPosition.getY() - movementDistance);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            nextPosition.setX(nextPosition.getX() + movementDistance);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            nextPosition.setX(nextPosition.getX() - movementDistance);
        }
        return nextPosition;
    }

    private boolean checkIfBlocked(Vector2f position) {
        Vector2i[][] blockedField = ((Play) EGameState.PLAY.getReference()).getMap().getBlockingLayerRenderer().getData();

        int fieldX = (int) (position.getX() / 48);
        int fieldY = (int) (position.getY() / 48);

        if(blockedField[fieldX][fieldY] == null) {
            return false;
        }
        return true;
    }
}
