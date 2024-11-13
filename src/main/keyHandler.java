package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener{

    public boolean upPressed , downPressed, leftPressed, rightPressed, topright, topleft, bottomright, bottomleft;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

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
