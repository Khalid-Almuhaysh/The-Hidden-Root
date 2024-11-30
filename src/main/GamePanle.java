package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import Entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanle extends JPanel implements Runnable{
    
    final int originaltilesize = 16;
    final int scale = 3;

    public final int tilesize = originaltilesize*scale;
    public final int maxscreencol = 20;
    public final int maxscreenrow = 12;
    public final int screenwidth = tilesize*maxscreencol; //960 pix
    public final int screenhight = tilesize*maxscreenrow; //576 pix

    public final int maxworldcol = 50;
    public final int maxworldrow = 50;
    public final int worldwidth = tilesize *maxworldcol;
    public final int worldhight = tilesize *maxworldrow;

    public String[] playerNames = new String[10]; // Stores the last 5 players' names
    public double[] playerTimes = new double[10]; // Stores the corresponding times for each player
    public String currentPlayerName = "";
    public boolean isMusicMuted = false;
    public boolean isFullscreen;

    int screenwidth2 = screenwidth;
    int screenhight2 = screenhight;
    BufferedImage tempscreen;
    Graphics2D g2;
    

    int FPS= 60;

    TileManager tileM = new TileManager(this);
    keyHandler keyH= new keyHandler(this);
    

    Sound music = new Sound();
    Sound se = new Sound();
    Sound menumusic = new Sound();
    Sound fastmusic = new Sound();
    
    
    public collisionChecker cchecker = new collisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    

    Thread gameThread;


    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject [10];

    public int gamestate;
    public final int playstate = 1;
    public final int pausestate = 2;
    public final int winstate = 3;
    public final int menustate = 0;
    




    int playerX=100;
    int playerY=100;
    int playerSpeed = 4;

    DatabaseHandler dbHandler;


    public GamePanle(){

        this.setPreferredSize(new Dimension(screenwidth,screenhight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        dbHandler = new DatabaseHandler();
    }
    public void toggleFullscreen() {
         isFullscreen = dbHandler.readFullscreenPreference();
       dbHandler.saveFullscreenPreference(!isFullscreen); // Toggle and save preference
   
       // Notify the user to restart the game
       javax.swing.JOptionPane.showMessageDialog(
           App.window,
           "Fullscreen mode has been " + (!isFullscreen ? "enabled" : "disabled") + ".\nPlease restart the game for changes to take effect."
       );
   
       // Exit the game to apply changes
       System.exit(0);
   }
    public void showNameInputDialog() {
        boolean wasFullScreen = false;

    // Temporarily exit fullscreen mode if active
    if (App.window.getGraphicsConfiguration().getDevice().getFullScreenWindow() != null) {
        App.window.getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
        wasFullScreen = true;
    }

    // Show input dialog
    String name = javax.swing.JOptionPane.showInputDialog(App.window, "Enter your name:");

    // Handle empty or null input
    currentPlayerName = (name != null && !name.trim().isEmpty()) ? name : "Player" + (int) (Math.random() * 1000);

    // Restore fullscreen mode if it was active
    if (wasFullScreen) {
        setfullscreen();
    }
    }

    public void setupGame() 
    {
        
        dbHandler.loadLeaderboard(this);
        aSetter.setObject();

        playMusic(5);
        gamestate = menustate;

        tempscreen = new BufferedImage(screenwidth, screenhight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempscreen.getGraphics();
        
        
            setfullscreen();
        
           
        
        



    }
    public void setfullscreen(){

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        gd.setFullScreenWindow(App.window);

        screenwidth2 = App.window.getWidth();
        screenhight2 = App.window.getHeight();
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

            drawtotemp();
            drawtoscreen();

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

        if(gamestate == playstate){
            player.update();
        }
        if (gamestate == pausestate) {
            pauseAllSounds();
        }
        if (gamestate == winstate) {
            if (currentPlayerName != null && !currentPlayerName.isEmpty()) {
                storePlayerScore();  // This will store the score and update the leaderboard
            }
        }
        if (gamestate == menustate){
            //nothing
        }
        
        
    }
    public void storePlayerScore() {
        if (dbHandler != null && currentPlayerName != null && !currentPlayerName.isEmpty()) {
            dbHandler.savePlayerScore(currentPlayerName, ui.playtime);
            dbHandler.loadLeaderboard(this);  // Load the leaderboard after saving the score
        }
        }

        public void drawtotemp(){
            
        if (gamestate == menustate) {
            // Clear the screen and redraw the menu
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, screenwidth, screenhight);
            ui.draw(g2);
            
        }
        else{
            
            tileM.draw(g2);

        

            //Object drawing
    
            for(int i=0; i<obj.length; i++)
            {
                if(obj[i] != null)
                {
                    obj[i].draw(g2, this);
                }
            }
    
            player.draw(g2);
    
            ui.draw(g2);
            
        }
        
        


        }
        public void drawtoscreen(){
            Graphics g = getGraphics();
            g.drawImage(tempscreen, 0, 0, screenwidth2,screenhight2,null);
            g.dispose();

        }

    
    public void restartToMainMenu() {
        // Stop game thread to avoid conflicts

        stopAllSounds();
        isMusicMuted = false;

        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
    
        // Reset game state to the main menu
        gamestate = menustate;
    
        // Reset player properties
        player.setdefaultvalues();
        player.haskey = 0;
        player.digonalspeed = 2.8284;
    
        // Reset objects
        aSetter.resetRandomSeed();
        aSetter.setObject();
    
        // Reset UI variables
        ui.playtime = 0;
        ui.messege = "";
        ui.messegecounter = 0;
        ui.commandnum = 0;
    
        // Reset KeyHandler variables
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        keyH.topright = false;
        keyH.topleft = false;
        keyH.bottomright = false;
        keyH.bottomleft = false;
    
        // Stop all music and play menu music
        menumusic.setFile(5);
        menumusic.play();
        menumusic.loop();
    
        // Restart the game thread to ensure proper functionality
        startgamethread();
    
        // Force a repaint to update the UI
        repaint();
    }
    public void shutdown() {
        dbHandler.close();  // Close the database connection when the game shuts down
    }
    
    

    public void pleyMM(int i){
        menumusic.setFile(i);
        menumusic.play();
        menumusic.loop();
    }
    public void stopMM(){
        menumusic.stop();
    }

    public void playMusic(int i)
    {
        music.reset(); // Reset the music clip before starting
        music.setFile(i);
        music.play();
        music.loop();
        
    }
    
    public void stopMusic()
    {
        if (music.clip != null) {
            music.stop();
        }
    }


    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
    public void stopSE(){
        se.stop();
    }

    public void playfastmusic(int i){
        fastmusic.setFile(i);
        fastmusic.play();
        fastmusic.loop();
    }
    public void stopfastmusic(){
        if (fastmusic != null) {
            fastmusic.stop();
        }
    }
    public void stopAllSounds() {
        // Stop all sound sources
        music.stop();
        menumusic.stop();
        fastmusic.stop();
        se.stop();

    // Reset sound clips
        music.reset();
        menumusic.reset();
        fastmusic.reset();
        se.reset();
    }
    public void pauseAllSounds() {
        // Pause all sounds when the game is paused
        music.pause();
        menumusic.pause();
        fastmusic.pause();
    }
    public void resumeAllSounds() {
        // Resume the music when the game is resumed
        if (gamestate == playstate) {
            music.resume();  // Resume main game music
        } else if (gamestate == menustate) {
            menumusic.resume();  // Resume main menu music
        }
    }
    

    
    

    public void resetLeaderboard() {
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = null;
            playerTimes[i] = 0.0;
        }
    }
}