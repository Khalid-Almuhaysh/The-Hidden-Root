package tile;
 import java.awt.image.BufferedImage;
 import java.awt.image.BufferedImageOp;
 import java.awt.*;
 import javax.swing.*;

import main.GamePanle;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;



public class TileManager {
    GamePanle gp;
    Tile[] tile;

    public TileManager(GamePanle gp)
    {
        this.gp = gp;
        tile = new Tile[10];

    }

    public void getTileImage(){
        try 
        {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));

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

        public void draw(Graphics2D g2)
        {
            g2.drawImage(tile[0].image, 0, 0,gp.tilesize ,gp.tilesize, null);
            
        }
    
}