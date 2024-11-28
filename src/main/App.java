package main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception 
    {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Hidden Root");

            GamePanle gamePanle = new GamePanle();
            window.add(gamePanle);

            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            
            gamePanle.setupGame();
             
            gamePanle.startgamethread();
    }
}
