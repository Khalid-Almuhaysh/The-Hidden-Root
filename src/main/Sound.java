package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];


    public Sound() 
    {

        soundURL[0] = getClass().getResource("sound/slowFunnyBit.wav");
        soundURL[1] = getClass().getResource("sound/coin.wav");
        soundURL[2] = getClass().getResource("sound/fanfare.wav");
        soundURL[3] = getClass().getResource("sound/powerup.wav");
        soundURL[4] = getClass().getResource("sound/unlock.wav");
        soundURL[5] = getClass().getResource("sound/menu_theme bit.wav");
        soundURL[6] = getClass().getResource("sound/fastFunnyBit.wav");
        
    }
  


public void setFile(int i)
{
    try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
        clip = AudioSystem.getClip();
        clip.open(ais);

         if (i == 0 || i == 6 || i == 5) {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-20.0f);
         }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void play()
{
    if (clip != null) {
        System.out.println("Playing sound: " + clip.toString());
        clip.start();
    }
}

public void loop()
{
    if (clip != null) {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
}

public void stop()
{
    if (clip != null) { // Null check to prevent calling stop on a null object
        System.out.println("Stopping sound: " + clip.toString());
        clip.stop();
    }
}

public void pause() {
    if (clip != null && clip.isRunning()) {
        clip.stop(); // Pause the clip by stopping it
    }
}

public void resume() {
    if (clip != null) {
        clip.start(); // Resume the clip
    }
}
public void reset() {
    // Reset the clip when it's no longer needed
    if (clip != null) {
        clip.flush(); // Clear the clip buffer
        clip.close(); // Close the clip and release resources
        clip = null;  // Set to null to ensure it's reset
    }
}

}




