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

        tile = new Tile[15];

        maptilenum = new int[gp.maxworldcol][gp.maxworldrow];

        getTileImage();
        loadmap("world03.txt");

    }

    public void getTileImage(){
        try 
        {
            tile[0] = new Tile();
            tile[0].image =ImageIO.read(getClass().getResourceAsStream("rest/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("rest/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("rest/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("rest/sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("rest/palmtree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("rest/dirt.png"));

            tile[6]= new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("rest/jungle_grass.png"));
            
            tile[7]= new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("rest/vpath.png"));
            
            tile[8]= new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("rest/hpath.png"));
            
            tile[9]= new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("rest/path.png"));

            tile[10]= new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("rest/bleft_path.png"));

            tile[11]= new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("rest/tleft_path.png"));

            tile[12]= new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("rest/bright_path.png"));

            tile[13]= new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("rest/tright_path.png"));

            tile[14]= new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("rest/yflower.png"));



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

            while (col < gp.maxworldcol && row < gp.maxworldrow) {

                String line = br.readLine();

                while (col < gp.maxworldcol) {
                    String numbers[]= line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    maptilenum[col][row]=num;

                    col++;
                }

                if(col== gp.maxworldcol){
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
            int worldcol = 0;
            int worldrow = 0;
           
            while(worldcol < gp.maxworldcol && worldrow < gp.maxworldrow){
                
                int tilenum= maptilenum[worldcol][worldrow];

                int worldx = worldcol * gp.tilesize;
                int worldy = worldrow * gp.tilesize;
                int screenx = worldx - gp.player.worldx + gp.player.screenx;
                int screeny = worldy - gp.player.worldy + gp.player.screeny;

                if(worldx + gp.tilesize > gp.player.worldx - gp.player.screenx &&
                   worldx - gp.tilesize < gp.player.worldx + gp.player.screenx && 
                   worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
                   worldy - gp.tilesize < gp.player.worldy + gp.player.screeny)
                   {

                        g2.drawImage(tile[tilenum].image, screenx, screeny, gp.tilesize, gp.tilesize, null);
                    }
                worldcol++;
                

                if(worldcol == gp.maxworldcol){
                    worldcol = 0;
                    worldrow++;
                    
                }
            }
            
        }
    
}