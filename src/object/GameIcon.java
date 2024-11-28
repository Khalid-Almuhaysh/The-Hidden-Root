package object;

import javax.imageio.ImageIO;

public class GameIcon extends SuperObject {

    public GameIcon()
    {
        name = "trophy";
        try{
            image = ImageIO.read(getClass().getResource("objects/game_icon.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
