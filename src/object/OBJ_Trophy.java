package object;

import javax.imageio.ImageIO;

public class OBJ_Trophy extends SuperObject{
    public OBJ_Trophy()
    {
        name = "trophy";
        try{
            image = ImageIO.read(getClass().getResource("objects/trophy.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
