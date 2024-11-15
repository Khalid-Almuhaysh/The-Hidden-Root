package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;


public class UI {

    GamePanle gp;
    Font mygameFont, mygamefont_bold;
    BufferedImage keyIamge;
    public boolean messegon =false;
    public String messege = "";
    int messegecounter = 0;
    public boolean gamefinshed = false;
    

    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanle gp){
        this.gp = gp;

        mygameFont = new Font("Arial", Font.PLAIN, 40);
        mygamefont_bold = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyIamge = key.image;
        
    }

    public void showmassge(String text){
        messege = text;
        messegon = true;
    }
    public void draw(Graphics2D g2){

        if(gamefinshed == true){


            g2.setFont(mygameFont);
            g2.setColor(Color.white);

            String text;
            int textlength;
            int x;
            int y;

            text = "You found the Chest";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/2 - textlength/2;
            y = gp.screenhight/2 -(gp.tilesize*3);
            g2.drawString(text, x, y);

            text = "Your time is: "+dFormat.format(playtime)+" !";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/2 - textlength/2;
            y = gp.screenhight/2 -(gp.tilesize*4);
            g2.drawString(text, x, y);

            g2.setFont(mygamefont_bold);
            g2.setColor(Color.yellow);
            text = "You win";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/2 - textlength/2;
            y = gp.screenhight/2 +(gp.tilesize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;


        }else{

            g2.setFont(mygameFont);
            g2.setColor(Color.white);
            g2.drawImage(keyIamge, gp.tilesize/2, gp.tilesize/2, gp.tilesize, gp.tilesize, null);
            g2.drawString("x "+ gp.player.haskey,74,65);

            playtime +=(double)1/60;
            g2.drawString("Time "+dFormat.format(playtime), gp.tilesize*11, 65);

            if(messegon == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(messege, gp.tilesize/2, gp.tilesize*5);
            }

            messegecounter++;

            if(messegecounter > 120){
                messegecounter = 0;
                messegon = false;
            }
        }
    }
}
