package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
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

    

    public Player(GamePanle gp, keyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;

        screenx = gp.screenwidth/2 - (gp.tilesize/2);
        screeny = gp.screenhight/2 - (gp.tilesize/2);

        setdefaultvalues();
        getplayerimage();
    }

    public void setdefaultvalues(){

        worldx = gp.tilesize*25;
        worldy = gp.tilesize*25;
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
    
            // Handle diagonal movement
            if (keyH.upPressed && keyH.rightPressed) {
                direction = "right";
                worldx += digonalspeed;
                worldy -= digonalspeed;
                isMovingDiagonally = true;
            } else if (keyH.upPressed && keyH.leftPressed) {
                direction = "left";
                worldx -= digonalspeed;
                worldy -= digonalspeed;
                isMovingDiagonally = true;
            } else if (keyH.downPressed && keyH.rightPressed) {
                direction = "right";
                worldx += digonalspeed;
                worldy += digonalspeed;
                isMovingDiagonally = true;
            } else if (keyH.downPressed && keyH.leftPressed) {
                direction = "left";
                worldx -= digonalspeed;
                worldy += digonalspeed;
                isMovingDiagonally = true;
            }
    
            // Handle non-diagonal movement if not moving diagonally
            if (!isMovingDiagonally) {
                if (keyH.upPressed) {
                    direction = "up";
                    worldy -= speed;
                } else if (keyH.downPressed) {
                    direction = "down";
                    worldy += speed;
                } else if (keyH.leftPressed) {
                    direction = "left";
                    worldx -= speed;
                } else if (keyH.rightPressed) {
                    direction = "right";
                    worldx += speed;
                }
            }
    
            // Update sprite animation
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

        }

        g2.drawImage(image, screenx, screeny, gp.tilesize , gp.tilesize, null);
        
        
    }
}
