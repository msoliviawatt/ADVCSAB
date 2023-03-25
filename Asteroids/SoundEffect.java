import java.io.*;
import javax.sound.sampled.*;

//this class is for the sound effects
public class SoundEffect {
    public static void playSound(String filename) {
        try {
            File soundFile = new File(filename); //creates a new sound file
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); //gets the audio input stream
            Clip clip = AudioSystem.getClip(); //gets the clip
            clip.open(audioIn); //opens the clip
            clip.start(); //starts the clip
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
