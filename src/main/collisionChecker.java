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
}
