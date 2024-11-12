package tile;
 import java.awt.image.BufferedImage;
 import java.awt.image.BufferedImageOp;
 import java.awt.*;
 import javax.swing.*;

import main.GamePanle;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;



public class TileManager {
    GamePanle gp;
    Tile[] tile;
    int maptilenum[][];

    public TileManager(GamePanle gp)
    {
        this.gp = gp;

        tile = new Tile[10];

        maptilenum = new int[gp.maxscreencol][gp.maxscreenrow];

        getTileImage();
        loadmap("map01.txt");

    }

    public void getTileImage(){
        try 
        {
            tile[0] = new Tile();
            tile[0].image =ImageIO.read(getClass().getResourceAsStream("grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("earth.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void loadmap(String filepass){
            try{
                InputStream is = getClass().getResourceAsStream(filepass);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;

                while (col < gp.maxscreencol && row < gp.maxscreenrow) {

                    String line = br.readLine();

                    while (col < gp.maxscreencol) {
                        String numbers[]= line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        maptilenum[col][row]=num;

                        col++;
                    }

                    if(col== gp.maxscreencol){
                        col=0;
                        row++;
                    }
                    
                }
                br.close();

            }catch(Exception e){

            }




        }

        public void draw(Graphics2D g2)
        {
            int col = 0;
            int row = 0;
            int x = 0;
            int y = 0;

            while(col < gp.maxscreencol && row < gp.maxscreenrow){
                
                int tilenum= maptilenum[col][row];
                
                g2.drawImage(tile[tilenum].image,x,y,gp.tilesize,gp.tilesize,null);
                col++;
                x += gp.tilesize;

                if(col == gp.maxscreencol){
                    col = 0;
                    x = 0;
                    row++;
                    y += gp.tilesize;
                }
            }
            
        }
    
}