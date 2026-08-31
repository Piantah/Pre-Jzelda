package JZ_View;

import javax.sound.sampled.*;
import java.io.*;

public class AudioManager {
    private static AudioManager instance;
    public static AudioManager getInstance(){
        if(instance==null) instance=new AudioManager();
        return instance;

    }
    private AudioManager(){

    }
    public void play(String filename){
        try{
            InputStream on = getClass().getResourceAsStream(filename);
            if (on == null) {
                System.err.println("Impossibile trovare il file audio: " + filename);
                return;
            }
            InputStream in = new BufferedInputStream(on);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    //metodo per le canzoni in loop
    public void playLoop(String filename){
        try{
            InputStream on = getClass().getResourceAsStream(filename);
            if (on == null) {
                System.err.println("Impossibile trovare il file audio: " + filename);
                return;
            }
            InputStream in = new BufferedInputStream(on);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    // =^.^=
}
