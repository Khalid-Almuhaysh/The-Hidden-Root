package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanle gp;

    public AssetSetter(GamePanle gp){
        this.gp = gp;
    }

    public void setObject()
    {
            //Beach Key(TopRight of the map)
            gp.obj[0] = new OBJ_Key();
            gp.obj[0].worldx = 37 * gp.tilesize;
            gp.obj[0].worldy = 11 * gp.tilesize;


            //Castle key(Bottom Left of the map)
            gp.obj[1] = new OBJ_Key();
            gp.obj[1].worldx = 34 * gp.tilesize;
            gp.obj[1].worldy =  36 * gp.tilesize;
            
            //Cliff of the island key(BottomRight of the map)
            gp.obj[2] = new OBJ_Key();
            gp.obj[2].worldx = 12 * gp.tilesize;
            gp.obj[2].worldy =  36 * gp.tilesize;

            //First door (Start of the Maze)
            gp.obj[3] = new OBJ_Door();
            gp.obj[3].worldx = 20 * gp.tilesize;
            gp.obj[3].worldy =  20 * gp.tilesize;

            //Second door (Middle of the Maze)
            gp.obj[4] = new OBJ_Door();
            gp.obj[4].worldx = 13 * gp.tilesize;
            gp.obj[4].worldy =  21 * gp.tilesize;


            //Third door(End of the Maze or The Chest Door) 
            gp.obj[5] = new OBJ_Door();
            gp.obj[5].worldx = 12 * gp.tilesize;
            gp.obj[5].worldy =  15 * gp.tilesize;
        
           
        
            gp.obj[6] = new OBJ_Chest();
            gp.obj[6].worldx = 12 * gp.tilesize;
            gp.obj[6].worldy =  12 * gp.tilesize;

            gp.obj[7] = new OBJ_Boots();
            gp.obj[7].worldx = 24 * gp.tilesize;
            gp.obj[7].worldy =  11 * gp.tilesize;


        
    }
}