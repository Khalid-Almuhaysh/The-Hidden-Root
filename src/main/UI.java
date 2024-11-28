package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.swing.JTextArea;

import object.GameIcon;
import object.OBJ_Key;
import object.OBJ_Trophy;


public class UI {

    GamePanle gp;
    Graphics2D g2;
    Font DefultFont;
    BufferedImage keyIamge;
    BufferedImage Trophy;
    BufferedImage Gameicon;
    public boolean messegon =false;
    public String messege = "";
    int messegecounter = 0;
    public boolean gamefinshed = false;
    public int commandnum = 0;
    public String currentPlayerName = "";  // Store the current player's name
    public double playtimer = 0;
    

    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanle gp){
        this.gp = gp;

        
        
        try {
            InputStream is  = getClass().getResourceAsStream("font/pcsenior.ttf");
            DefultFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch(FontFormatException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        OBJ_Key key = new OBJ_Key();
        OBJ_Trophy trophy = new OBJ_Trophy();
        GameIcon gameIcon = new GameIcon();
        keyIamge = key.image;
        Trophy = trophy.image;
        Gameicon = gameIcon.image;
        
    }

    public void showmassge(String text){
        messege = text;
        messegon = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(DefultFont);
        g2.setColor(Color.white);

        String text;
        int textlength;
        int x;
        int y;

        if(gp.gamestate == gp.menustate){
            drawtitlesscreen();
        }


        if(gp.gamestate == gp.winstate){


            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23));
            text = "You found the Chest";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/4 +(30) - textlength/2;
            y = gp.screenhight/2 -(gp.tilesize*3);
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            text = "Your time is: "+dFormat.format(playtime)+" !";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/4 +(30) - textlength/2;
            y = gp.screenhight/2 -(gp.tilesize*4);
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 55));
            g2.setColor(Color.yellow);
            text = "You win";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/4 + (30) - textlength/2;
            y = gp.screenhight/2 +(gp.tilesize*2);
            g2.drawString(text, x, y);
            leaderbord(g2);

            gp.gameThread = null;

            
          





        }
        else if(gp.gamestate == gp.playstate){

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
            g2.setColor(Color.white);
            g2.drawImage(keyIamge, gp.tilesize/2, gp.tilesize/2, gp.tilesize, gp.tilesize, null);
            g2.drawString("x "+ gp.player.haskey,74,65);

            playtime +=(double)1/60;
            g2.drawString("Time "+dFormat.format(playtime), gp.tilesize*11, 65);
            

            if(messegon == true){
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
                g2.drawString(messege, gp.tilesize/2, gp.tilesize*5);
            }

            messegecounter++;

            if(messegecounter > 120){
                messegecounter = 0;
                messegon = false;
            }
        }
        else if (gp.gamestate == gp.pausestate) {
            
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
            g2.setColor(Color.WHITE);
            text = "Game Pused";
            textlength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenwidth/2 - textlength/2;
            y = gp.screenhight/2 ;
            g2.drawString(text, x, y);
        }
    }

    public void leaderbord(Graphics2D g2){
        int box_x = gp.tilesize * 10 - (10);
        int box_y = 10;
        int box_width = gp.tilesize * 6;
        int box_height = gp.tilesize * 11 + (35);
        int text_x;
        int text_y;
        String name;
        String time;

        String text;
        int textlength;

        g2.setColor(Color.BLACK);
        g2.fillRoundRect(box_x, box_y, box_width, box_height, 35, 35);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
        g2.setColor(Color.WHITE);
        text = "Leaderboard!";
        textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        text_x = box_x + gp.tilesize * 2 - (10);
        text_y = box_y + gp.tilesize;
        g2.drawString(text, text_x, text_y);
        g2.drawImage(Trophy, text_x - gp.tilesize, text_y - gp.tilesize + (15), gp.tilesize, gp.tilesize, null);

        // Display the names and times in the leaderboard
        for (int i = 0; i < gp.playerNames.length; i++) {
            if (gp.playerNames[i] != null && !gp.playerNames[i].isEmpty()) {
                name = gp.playerNames[i];
                time = String.format("%.2f", gp.playerTimes[i]);
                text_x = box_x + 10;
                text_y = box_y + (gp.tilesize * (i + 2)); // Spacing between each entry
                g2.drawString(name + " - " + time + "s", text_x, text_y);
            }
        }
    }
    public void drawtitlesscreen() {
        int text_x;
        int text_y;
    
        String text;
        int textlength;
    
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45));
        g2.setColor(Color.GRAY);
    
        text_x = gp.tilesize;
        text_y = gp.tilesize * 2;
        text = "THE HIDDEN ROOT";
        textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    
        g2.drawString(text, text_x + 5, text_y + 5);
    
        g2.setColor(Color.WHITE);
    
        g2.drawString(text, text_x, text_y);
        g2.drawImage(Gameicon, gp.tilesize * 6, gp.tilesize * 3, gp.tilesize * 4, gp.tilesize * 4, null);
    
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
    
        text_x = gp.tilesize * 6 + 20;
        text_y = gp.tilesize * 8;
        text = "New Game";
        textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    
        g2.setColor(Color.WHITE);
    
        g2.drawString(text, text_x, text_y);
    
        if (commandnum == 0) {
            g2.drawString(">", text_x - gp.tilesize + 10, text_y);
        }
    
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
    
        text_x = gp.tilesize * 7 + 10;
        text_y = gp.tilesize * 9 + 10;
        text = "Quit";
        textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    
        g2.drawString(text, text_x, text_y);
        if (commandnum == 1) {
            g2.drawString(">", text_x - gp.tilesize + 10, text_y);
        }
    }
    
}
