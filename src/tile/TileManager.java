package tile;
 import java.awt.image.BufferedImage;
 import java.awt.image.BufferedImageOp;
 import java.awt.*;
 import javax.swing.*;

import main.GamePanle;
import main.collisionChecker;
import main.keyHandler;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;



public class TileManager {
    GamePanle gp;
    public Tile[] tile;
    public int maptilenum[][];
    

    public TileManager(GamePanle gp)
    {
        this.gp = gp;
        

        tile = new Tile[50];

        maptilenum = new int[gp.maxworldcol][gp.maxworldrow];

        getTileImage();
        loadmap("worldV1.txt");

    }

    public void getTileImage(){
        try 
        {   
            tile[0] = new Tile();
            tile[0].image =ImageIO.read(getClass().getResourceAsStream("rest/grass1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("rest/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("rest/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("rest/sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("rest/palmtree1.png"));
            tile[4].treecollision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("rest/dirt.png"));

            tile[6]= new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("rest/jungle_grass.png"));
            
            tile[7]= new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("rest/sandtree.png"));
            tile[7].treecollision = true;
            
            tile[8]= new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("rest/tree1.png"));
            tile[8].treecollision = true;
            
            tile[9]= new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("rest/yflower1.png"));

            tile[10]= new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("rest/grass2.png"));

            tile[11]= new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("rest/spath.png"));

            tile[12]= new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("rest/svpath.png"));

            tile[13]= new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("rest/shpath.png"));

            tile[14]= new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("rest/spathbottomleft.png"));

            tile[15]= new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("rest/spathbottomright.png"));

            tile[16]= new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("rest/spathtopleft.png"));

            tile[17]= new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("rest/spathtopright.png"));

            tile[18]= new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("rest/dhpathleft.png"));
            
            tile[19]= new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("rest/dhpathright.png"));

            tile[20]= new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("rest/dpathbottomleft.png"));

            tile[21]= new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("rest/dpathbottomright.png"));

            tile[22]= new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("rest/dpathleft.png"));

            tile[23]= new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("rest/dpathright.png"));

            tile[24]= new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("rest/dpathtopleft.png"));
            
            tile[25]= new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("rest/dpathtopright.png"));

            tile[26]= new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("rest/tile000.png"));

            tile[27]= new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("rest/tile001.png"));
            
            tile[28]= new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("rest/tile002.png"));

            tile[29]= new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("rest/tile003.png"));

            tile[30]= new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("rest/tile004.png"));

            tile[31]= new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("rest/tile005.png"));
            tile[31].collision = true;

            tile[32]= new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("rest/tile006.png"));
            tile[32].collision = true;

            tile[33]= new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("rest/tile007.png"));

            tile[34]= new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("rest/tile008.png"));

            tile[35]= new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("rest/tile009.png"));
            tile[35].collision = true;

            tile[36]= new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("rest/tile010.png"));
            tile[36].collision = true;

            tile[37]= new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("rest/tile011.png"));

            tile[38]= new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("rest/tile012.png"));

            tile[39]= new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("rest/tile013.png"));
            tile[39].collision = true;

            tile[40]= new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("rest/tile014.png"));
            tile[40].collision = true;

            tile[41]= new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("rest/tile015.png"));
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