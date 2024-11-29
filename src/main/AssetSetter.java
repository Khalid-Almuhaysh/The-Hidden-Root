package main;

import java.util.Random;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanle gp;
    private Random random; // Declare the Random object

    public AssetSetter(GamePanle gp) {
        this.gp = gp;
        this.random = new Random(); // Initialize Random object
    }

    public int getRandomNumber() {
        return random.nextInt(3) + 1; // Generates 1, 2, or 3
    }

    public void setObject() {
        int randomValue = getRandomNumber(); // Generate a new random value for this game
        System.out.println("Random value generated: " + randomValue);

        if (randomValue == 1) {
            gp.obj[0] = new OBJ_Key();
            gp.obj[0].worldx = 37 * gp.tilesize;
            gp.obj[0].worldy = 11 * gp.tilesize;

            gp.obj[1] = new OBJ_Key();
            gp.obj[1].worldx = 34 * gp.tilesize;
            gp.obj[1].worldy = 36 * gp.tilesize;

            gp.obj[2] = new OBJ_Key();
            gp.obj[2].worldx = 12 * gp.tilesize;
            gp.obj[2].worldy = 36 * gp.tilesize;

            gp.obj[7] = new OBJ_Boots();
            gp.obj[7].worldx = 24 * gp.tilesize;
            gp.obj[7].worldy = 11 * gp.tilesize;
        } else if (randomValue == 2) {
            gp.obj[0] = new OBJ_Key();
            gp.obj[0].worldx = 29 * gp.tilesize;
            gp.obj[0].worldy = 30 * gp.tilesize;

            gp.obj[1] = new OBJ_Key();
            gp.obj[1].worldx = 11 * gp.tilesize;
            gp.obj[1].worldy = 28 * gp.tilesize;

            gp.obj[2] = new OBJ_Key();
            gp.obj[2].worldx = 19 * gp.tilesize;
            gp.obj[2].worldy = 15 * gp.tilesize;

            gp.obj[7] = new OBJ_Boots();
            gp.obj[7].worldx = 30 * gp.tilesize;
            gp.obj[7].worldy = 16 * gp.tilesize;
        } else if (randomValue == 3) {
            gp.obj[0] = new OBJ_Key();
            gp.obj[0].worldx = 37 * gp.tilesize;
            gp.obj[0].worldy = 11 * gp.tilesize;

            gp.obj[1] = new OBJ_Key();
            gp.obj[1].worldx = 21 * gp.tilesize;
            gp.obj[1].worldy = 32 * gp.tilesize;

            gp.obj[2] = new OBJ_Key();
            gp.obj[2].worldx = 10 * gp.tilesize;
            gp.obj[2].worldy = 24 * gp.tilesize;

            gp.obj[7] = new OBJ_Boots();
            gp.obj[7].worldx = 37 * gp.tilesize;
            gp.obj[7].worldy = 27 * gp.tilesize;
        }

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldx = 20 * gp.tilesize;
        gp.obj[3].worldy = 20 * gp.tilesize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldx = 13 * gp.tilesize;
        gp.obj[4].worldy = 21 * gp.tilesize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldx = 12 * gp.tilesize;
        gp.obj[5].worldy = 15 * gp.tilesize;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldx = 12 * gp.tilesize;
        gp.obj[6].worldy = 12 * gp.tilesize;
    }

    public void resetRandomSeed() {
        random = new Random(); // Reset the Random object to produce new random values
    }
}