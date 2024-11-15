package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldx, worldy;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spritecounter = 0;
    public int spritnum = 1;
    public Rectangle solidarea;
    public int solidareadefaultx,solidareadefaulty;
    public boolean collisionOn = false;

}
