package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Entity.Entity;
import tile.Tile;

public class collisionChecker {

    GamePanle gp;

    public collisionChecker(GamePanle gp){
        this.gp = gp;

    }

       public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldx + entity.solidarea.x;
        int entityRightWorldX = entity.worldx + entity.solidarea.x + entity.solidarea.width;
        int entityTopWorldY = entity.worldy + entity.solidarea.y;
        int entityBottomWorldY = entity.worldy + entity.solidarea.y + entity.solidarea.height;

        int entityLeftCol = entityLeftWorldX / gp.tilesize;
        int entityRightCol = entityRightWorldX / gp.tilesize;
        int entityTopRow = entityTopWorldY / gp.tilesize;
        int entityBottomRow = entityBottomWorldY / gp.tilesize;

        int tileNum1, tileNum2, tileNum3;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityTopRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityBottomRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityLeftCol][entityBottomRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityBottomRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "topright":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tilesize;
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                tileNum3 = gp.tileM.maptilenum[entityRightCol][entityBottomRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity) || isTileColliding(tileNum3, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "topleft":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tilesize;
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityTopRow];
                tileNum3 = gp.tileM.maptilenum[entityLeftCol][entityBottomRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity) || isTileColliding(tileNum3, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "bottomright":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tilesize;
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityRightCol][entityBottomRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityTopRow];
                tileNum3 = gp.tileM.maptilenum[entityLeftCol][entityBottomRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity) || isTileColliding(tileNum3, entity)) {
                    entity.collisionOn = true;
                }
                break;

            case "bottomleft":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tilesize;
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tilesize;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityBottomRow];
                tileNum3 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                if (isTileColliding(tileNum1, entity) || isTileColliding(tileNum2, entity) || isTileColliding(tileNum3, entity)) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public boolean isTileColliding(int tileNum, Entity entity) {
        Tile tile = gp.tileM.tile[tileNum];
        if (tile.treecollision) {
            return checkTreeCollision(entity, tile);
        }
        return tile.collision;
    }

    public boolean checkTreeCollision(Entity entity, Tile tile) {
        int halfWidth = entity.solidarea.width / 3;
        int halfHeight = entity.solidarea.height / 3 ;

        Rectangle treeCollisionArea = new Rectangle(
                entity.solidarea.x + halfWidth / 2,
                entity.solidarea.y + halfHeight / 2,
                halfWidth,
                halfHeight
        );

        return entity.solidarea.intersects(treeCollisionArea);
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
