package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImageOp;
import javax.swing.*;

import javax.imageio.ImageIO;

import main.GamePanle;
import main.keyHandler;

public class Player extends Entity{
    
    GamePanle gp;
    keyHandler keyH;

    public final int screenx;
    public final int screeny;
    int haskey = 0;

    

    public Player(GamePanle gp, keyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;

        screenx =  gp.screenwidth/2 - (gp.tilesize/2);  
        screeny =  gp.screenhight/2 - (gp.tilesize/2);

        solidarea = new Rectangle();
        solidarea.x = 12;
        solidarea.y = 20;
          solidareadefaultx = solidarea.x;
          solidareadefaulty = solidarea.y;
        solidarea.width = 24;
        solidarea.height = 24;

        setdefaultvalues();
        getplayerimage();
    }

    public void setdefaultvalues(){

        worldx = gp.tilesize*23;
        worldy = gp.tilesize*23;
        speed= 4;
        direction ="down";
    }
    public void getplayerimage(){
        
        try {

            up1 = ImageIO.read(getClass().getResource("res/player_char/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("res/player_char/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResource("res/player_char/boy_down_1.png"));;
            down2 = ImageIO.read(getClass().getResource("res/player_char/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("res/player_char/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("res/player_char/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("res/player_char/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("res/player_char/boy_right_2.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        boolean isMovingDiagonally = false;
        double digonalspeed = 2.8284;
        

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
    
           
            if (keyH.upPressed && keyH.rightPressed) {
                direction = "topright";
                isMovingDiagonally = true;
            } else if (keyH.upPressed && keyH.leftPressed) {
                direction = "topleft";
               
                isMovingDiagonally = true;
            } else if (keyH.downPressed && keyH.rightPressed) {
                direction = "bottomright";
                
                isMovingDiagonally = true;
            } else if (keyH.downPressed && keyH.leftPressed) {
                direction = "bottomleft";
                
                isMovingDiagonally = true;
            }
    
           
            if (!isMovingDiagonally) {
                if (keyH.upPressed) {
                    direction = "up";
                    
                } else if (keyH.downPressed) {
                    direction = "down";
                    
                } else if (keyH.leftPressed) {
                    direction = "left";
                    
                } else if (keyH.rightPressed) {
                    direction = "right";
                    
                }
            }

            collisionOn = false;
            gp.cchecker.checkTile(this);

            //check object collision
            int objinedex = gp.cchecker.checkObject(this, true);
            pickupObject(objinedex);

            if(collisionOn == false){
                switch (direction) {
                    case "up":
                    worldy -= speed;
                        
                        break;
                    case "down":
                    worldy += speed;
                        
                        break;
                    case "left":
                    worldx -= speed;
                        break;
                    case "right":
                    worldx += speed;
                        break;

                    case "topright":
                    worldx += digonalspeed;
                    worldy -= digonalspeed;
                    break;
                    case "bottomright":
                    worldx += digonalspeed;
                    worldy += digonalspeed;
                    break;
                    case "topleft":
                    worldx -= digonalspeed;
                    worldy -= digonalspeed;
                    break;
                    case "bottomleft":
                    worldx -= digonalspeed;
                    worldy += digonalspeed;
                    break;
                    
                }
            }

    
            
            spritecounter++;
            if (spritecounter >= 12) {
                if (spritnum == 1) {
                    spritnum = 2;
                } else if (spritnum == 2) {
                    spritnum = 1;
                }
                spritecounter = 0;
            }
        }
    }

    public void pickupObject(int i)
    {
        if (i != 999)
        {
           

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    haskey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + haskey);
                    break;
                case "Door":
                    gp.playSE(4);
                    if (haskey > 0) 
                    {
                        gp.obj[i] = null;
                        haskey--;    
                    }
                    System.out.println("Key: " + haskey);
                    break;
                case "Boots":
                    gp.playSE(3);
                    speed += 2;
                    gp.obj[i] = null;
                    System.out.println("Speed increased by " + speed);
            }
        }
    }
    

    public void draw(Graphics2D g2){

        BufferedImage image= null;

        switch (direction) {
            case "up":
            if(spritnum == 1){
                image = up1;
            }
            if(spritnum == 2){
                image = up2;
            }

                break;

            case "down":
                if(spritnum == 1){
                image = down1;
            }
            if(spritnum == 2){
                image = down2;
            }
                break;

            case "left":
            if(spritnum == 1){
                image = left1;
            }
            if(spritnum == 2){
                image = left2;
            }
                break;

            case "right":
            if(spritnum == 1){
                image = right1;
            }
            if(spritnum == 2){
                image = right2;
            }
                break;

            case "topright":
            if(spritnum == 1){
                image = right1;
            }
            if(spritnum == 2){
                image = right2;
            }
            break;

            
            case "topleft":
            if(spritnum == 1){
                image = left1;
            }
            if(spritnum == 2){
                image = left2;
            }
                break;
                
            case "bottomleft":
            if(spritnum == 1){
                image = left1;
            }
            if(spritnum == 2){
                image = left2;
            }
                break;
                case "bottomright":
            if(spritnum == 1){
                image = right1;
            }
            if(spritnum == 2){
                image = right2;
            }
            break;

        }
        
        g2.drawImage(image, screenx, screeny, gp.tilesize , gp.tilesize, null);
        //g2.setColor(Color.red);
        //g2.drawRect(screenx+solidarea.x, screeny + solidarea.y, solidarea.width, solidarea.height);
        
        
    }
}
