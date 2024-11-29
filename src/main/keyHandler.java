package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener{

    public boolean upPressed , downPressed, leftPressed, rightPressed,
     topright, topleft, bottomright, bottomleft,
     debug, restart;
     
     GamePanle gp;

     public keyHandler(GamePanle gp){
        this.gp = gp;
     }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(gp.gamestate == gp.menustate){
            if(code == KeyEvent.VK_W){
                gp.ui.commandnum--;
                if (gp.ui.commandnum < 0 ) {
                    gp.ui.commandnum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandnum++;
                if (gp.ui.commandnum >1 ) {
                    gp.ui.commandnum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandnum == 0){
                    gp.showNameInputDialog();
                    gp.gamestate = gp.playstate;
                    gp.stopMM();  // Stop the menu music
                    gp.playMusic(0); // Start the game music
                }
                else if(gp.ui.commandnum == 1){
                    System.exit(0);
                }
            }
        }
        if (gp.gamestate == gp.winstate && code == KeyEvent.VK_SPACE) {
            gp.restartToMainMenu();
            
        }

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_W && code == KeyEvent.VK_D){
            topright = true;
        }
        if (code == KeyEvent.VK_W && code == KeyEvent.VK_A) {
            topleft = true;
        }
        if (code == KeyEvent.VK_S && code == KeyEvent.VK_D) {
            bottomright = true;
        }
        if (code == KeyEvent.VK_S && code == KeyEvent.VK_A) {
            bottomleft = true;
        }
        if(code == KeyEvent.VK_F1){
            if(debug == false){
                debug = true;
            }else if(debug == true){
                debug = false;
            }
        
        }
        
        if(code == KeyEvent.VK_ESCAPE){
            if (gp.gamestate == gp.playstate) {
                gp.gamestate = gp.pausestate;
                gp.pauseAllSounds(); 
            }
            else if (gp.gamestate == gp.pausestate) {
                gp.gamestate = gp.playstate;
                gp.resumeAllSounds();
            }
        }
        if(code == KeyEvent.VK_BACK_SPACE){
            if (gp.gamestate == gp.playstate || gp.gamestate == gp.pausestate || gp.gamestate == gp.menustate) {
                gp.gamestate = gp.winstate;
                gp.resumeAllSounds();
            }

        }

        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_W && code == KeyEvent.VK_D){
            topright = false;
        }
        if (code == KeyEvent.VK_W && code == KeyEvent.VK_A) {
            topleft = false;
        }
        if (code == KeyEvent.VK_S && code == KeyEvent.VK_D) {
            bottomright = false;
        }
        if (code == KeyEvent.VK_S && code == KeyEvent.VK_A) {
            bottomleft = false;
        }
    }
    
}
