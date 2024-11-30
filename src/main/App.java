package main;
import javax.swing.JFrame;

public class App {

    public static JFrame window;
    public static void main(String[] args) throws Exception 
    {
        DatabaseHandler dbHandler = new DatabaseHandler();
        boolean isFullscreen = dbHandler.readFullscreenPreference();

            window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Hidden Root");

           
            window.setUndecorated(true); // Enable fullscreen mode
        

            GamePanle gamePanle = new GamePanle();
            window.add(gamePanle);

            window.pack();

           
                window.setLocationRelativeTo(null); // Center window if not fullscreen
            

            window.setVisible(true);
            
            gamePanle.setupGame();
             
            gamePanle.startgamethread();
    }
}
