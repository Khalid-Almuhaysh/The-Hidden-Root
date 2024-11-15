package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import Entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanle extends JPanel implements Runnable{
    
    final int originaltilesize = 16;
    final int scale = 3;

    public final int tilesize = originaltilesize*scale;
    public final int maxscreencol = 16;
    public final int maxscreenrow = 12;
    public final int screenwidth = tilesize*maxscreencol; //768 pix
    public final int screenhight = tilesize*maxscreenrow; //576 pix

    public final int maxworldcol = 50;
    public final int maxworldrow = 50;
    public final int worldwidth = tilesize *maxworldcol;
    public final int worldhight = tilesize *maxworldrow;

    int FPS= 60;

    TileManager tileM = new TileManager(this);
    keyHandler keyH= new keyHandler();

    Sound sound = new Sound();
    
    public collisionChecker cchecker = new collisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;


    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject [10];
    




    int playerX=100;
    int playerY=100;
    int playerSpeed = 4;

    public GamePanle(){

        this.setPreferredSize(new Dimension(screenwidth,screenhight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() 
    {
        aSetter.setObject();

        playMusic(0);

    }

    public void startgamethread()
{

    gameThread= new Thread(this);
    gameThread.start();
}
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
         long timer = 0;
         int drawcount = 0;
        
        

       while(gameThread != null){

        currentTime = System.nanoTime();
        
        delta += (currentTime - lastTime) / drawInterval;
        timer += (currentTime - lastTime);

        lastTime = currentTime;

        if(delta >= 1){
            update();

            repaint();

            delta--;

            drawcount++;
    
         }

         if(timer >=1000000000){
            System.out.println("FPS" + drawcount);
            drawcount = 0;
            timer = 0;
         }
        
       }
    }   
    
    public void update(){

        player.update();
        
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        //Object drawing

        for(int i=0; i<obj.length; i++)
        {
            if(obj[i] != null)
            {
                obj[i].draw(g2, this);
            }
        }
        

        g2.dispose();
    }
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();
        
    }
    public void stopMusic()
    {
        sound.stop();
    }
    public void playSE(int i)
    {
        sound.setFile(i);
        sound.play();
    }

}
