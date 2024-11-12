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

    

    public Player(GamePanle gp, keyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;

        setdefaultvalues();
        getplayerimage();
    }

    public void setdefaultvalues(){

        x = 100;
        y = 100;
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

        if(keyH.upPressed == true || keyH.downPressed == true ||
         keyH.leftPressed == true || keyH.rightPressed == true ){
        
        if(keyH.upPressed == true){
            direction = "up";
            y -= speed;
        }
        if(keyH.downPressed == true){
            direction = "down";
            y += speed;
        }
        if(keyH.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        if(keyH.rightPressed == true){
            direction = "right";
            x += speed;
        }

        spritecounter++;
        if(spritecounter >= 12){
            if(spritnum == 1){
                spritnum = 2;
            }
            else if(spritnum == 2){
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

        g2.drawImage(image, x, y, gp.tilesize , gp.tilesize, null);
        
        
    }
}
