package main;

import Entity.Entity;

public class collisionChecker {

    GamePanle gp;

    public collisionChecker(GamePanle gp){
        this.gp = gp;

    }

    public void checkTile(Entity entity){

        int entityleftworldx = entity.worldx + entity.solidarea.x;
        int entityrightworldx = entity.worldx + entity.solidarea.x + entity.solidarea.width;
        int entitytopworldy = entity.worldy + entity.solidarea.y;
        int entitybottomworldy = entity.worldy + entity.solidarea.y + entity.solidarea.height;

        int entityleftcol = entityleftworldx/gp.tilesize;
        int entityrightcol = entityrightworldx/gp.tilesize;
        int entitytoprow = entitytopworldy/gp.tilesize;
        int entitybottomrow = entitybottomworldy/gp.tilesize;

        int tilenum1, tilenum2, tilenum3;

        switch (entity.direction) {
            case "up":
            
            entitytoprow = (entitytopworldy - entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityleftcol][entitytoprow];
            tilenum2 = gp.tileM.maptilenum[entityrightcol][entitytoprow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true){
                entity.collisionOn = true;
            }

                break;
            case "down":
            entitybottomrow = (entitybottomworldy + entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityleftcol][entitybottomrow];
            tilenum2 = gp.tileM.maptilenum[entityrightcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true){
                entity.collisionOn = true;
            }
                break;
            case "left":
            entityleftcol = (entityleftworldx - entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityleftcol][entitytoprow];
            tilenum2 = gp.tileM.maptilenum[entityleftcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true){
                entity.collisionOn = true;
            }
                break;
            case "right":
            entityrightcol = (entityrightworldx + entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityrightcol][entitytoprow];
            tilenum2 = gp.tileM.maptilenum[entityrightcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true){
                entity.collisionOn = true;
            }
                break;

            case "topright":
            entityrightcol = (entityrightworldx + entity.speed)/ gp.tilesize;
            entitytoprow = (entitytopworldy - entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityrightcol][entitytoprow];
            tilenum2 = gp.tileM.maptilenum[entityleftcol][entitytoprow];
            tilenum3 = gp.tileM.maptilenum[entityrightcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true || gp.tileM.tile[tilenum3].collision == true){
                entity.collisionOn = true;
            }
                break;

                case "topleft":
            entityleftcol = (entityleftworldx + entity.speed)/ gp.tilesize;
            entitytoprow = (entitytopworldy - entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityrightcol][entitytoprow];
            tilenum2 = gp.tileM.maptilenum[entityleftcol][entitytoprow];
            tilenum3 = gp.tileM.maptilenum[entityleftcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true || gp.tileM.tile[tilenum3].collision == true){
                entity.collisionOn = true;
            }
                break;

                case "bottomright":
            entityrightcol = (entityrightworldx + entity.speed)/ gp.tilesize;
            entitybottomrow = (entitybottomworldy - entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityrightcol][entitytoprow];
            tilenum2 = gp.tileM.maptilenum[entityleftcol][entitybottomrow];
            tilenum3 = gp.tileM.maptilenum[entityrightcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true || gp.tileM.tile[tilenum3].collision == true){
                entity.collisionOn = true;
            }
                break;

                case "bottomleft":
            entityleftcol = (entityleftworldx + entity.speed)/ gp.tilesize;
            entitybottomrow = (entitybottomworldy - entity.speed)/ gp.tilesize;
            tilenum1 = gp.tileM.maptilenum[entityrightcol][entitybottomrow];
            tilenum2 = gp.tileM.maptilenum[entityleftcol][entitytoprow];
            tilenum3 = gp.tileM.maptilenum[entityleftcol][entitybottomrow];

            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true || gp.tileM.tile[tilenum3].collision == true){
                entity.collisionOn = true;
            }
                break;

        }


    }

    public int checkObject(Entity entity,boolean player)
    {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++)
        {
            if(gp.obj[i] != null )
            {
                //getting entity solid area position
                entity.solidarea.x = entity.worldx + entity.solidarea.x;
                entity.solidarea.y = entity.worldy + entity.solidarea.y;

                // get the object's solid area position
                gp.obj[i].solidarea.x = gp.obj[i].worldx + gp.obj[i].solidarea.x;
                gp.obj[i].solidarea.y = gp.obj[i].worldy + gp.obj[i].solidarea.y;

                switch(entity.direction)
                {
                    case "up":
                        entity.solidarea.y -= entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea))
                        {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true)
                            {
                                index = i;

                            }
                        }
                        break;
                    case "down":
                        entity.solidarea.y += entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea))
                        {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            
                            if(player == true)
                            {
                                index = i;

                            }
                        }
                        break;
                    case "left":
                        entity.solidarea.x -= entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea))
                        {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            
                            if(player == true)
                            {
                                index = i;

                            }
                        }
                        break;
                    case "right":
                        entity.solidarea.x += entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea))
                        {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            
                            if(player == true)
                            {
                                index = i;

                            }
                        }
                        break;
                        
                }
                entity.solidarea.x = entity.solidareadefaultx; 
                entity.solidarea.y = entity.solidareadefaulty;
                gp.obj[i].solidarea.x = gp.obj[i].solidareadefaultx;
                gp.obj[i].solidarea.y = gp.obj[i].solidareadefaulty;
            }
        }

        return index;
    }
}
