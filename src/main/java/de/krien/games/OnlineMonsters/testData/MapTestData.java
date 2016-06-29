package de.krien.games.OnlineMonsters.testData;

import de.krien.games.OnlineMonsters.model.util.vector.Vector2i;

public class MapTestData {

    public static Vector2i[][] getGroundTestData() {
        Vector2i[][] groundTestData = new Vector2i[64][64];
        for(int x = 0; x < groundTestData.length; x++) {
            for (int y = 0; y < groundTestData[x].length; y++) {
                groundTestData[x][y] = new Vector2i(0, 0);
            }
        }
        groundTestData[2][2] = new Vector2i(4, 0);
        groundTestData[3][2] = new Vector2i(5, 0);
        groundTestData[4][2] = new Vector2i(6, 0);

        groundTestData[2][3] = new Vector2i(4, 1);
        groundTestData[3][3] = new Vector2i(5, 1);
        groundTestData[4][3] = new Vector2i(6, 1);

        groundTestData[2][4] = new Vector2i(4, 2);
        groundTestData[3][4] = new Vector2i(5, 2);
        groundTestData[4][4] = new Vector2i(6, 2);
        return groundTestData;
    }

    public static Vector2i[][] getBlockingTestData() {
        Vector2i[][] blockingTestData = new Vector2i[64][64];
        for(int x = 10; x < 13; x++) {
            blockingTestData[x][9] = new Vector2i(0, 0);
            for (int y = 10; y < 13; y++) {
                blockingTestData[x][y] = new Vector2i(0, 1);
            }
            blockingTestData[x][13] = new Vector2i(0, 2);
        }
        return blockingTestData;
    }

}
