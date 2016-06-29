package de.krien.games.OnlineMonsters.model.world;

import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;
import de.krien.games.OnlineMonsters.model.world.renderer.LayerRenderer;
import de.krien.games.OnlineMonsters.testData.MapTestData;
import de.krien.games.OnlineMonsters.view.texture.TextureUtil;

public class Map {

    private LayerRenderer groundLayerRenderer;
    private LayerRenderer blockingLayerRenderer;

    public void init() {
        Vector2i[][] groundTestData = MapTestData.getGroundTestData(); // TODO Get Map-Data from server
        groundLayerRenderer = new LayerRenderer(TextureUtil.getGroundTiles(), groundTestData);

        Vector2i[][] blockingTestData = MapTestData.getBlockingTestData(); // TODO Get Map-Data from server
        blockingLayerRenderer = new LayerRenderer(TextureUtil.getBlockingTiles(), blockingTestData);

        groundLayerRenderer.render();
        blockingLayerRenderer.render();
    }

    public void draw() {
        groundLayerRenderer.draw();
        blockingLayerRenderer.draw();
    }

    public void update() {

    }

    public LayerRenderer getGroundLayerRenderer() {
        return groundLayerRenderer;
    }

    public LayerRenderer getBlockingLayerRenderer() {
        return blockingLayerRenderer;
    }
}
