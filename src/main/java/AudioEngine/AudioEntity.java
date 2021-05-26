package AudioEngine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


enum PlaybackMode {
    regular, loop
}

enum PlaybackStatus {
    playing,paused,stopped
}


public class AudioEntity {
    
    // To store current position
    Long currentFrame;
    
    File audiofile;
    Clip clip;
    
    // current status of clip
    PlaybackStatus status = PlaybackStatus.stopped;
    PlaybackMode mode = PlaybackMode.regular;
    
    AudioInputStream audioInputStream;
    
    // Constructor to initialize streams and clip
    public AudioEntity(File audiofile, PlaybackMode mode) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Create AudioInputStream object
        this.audiofile = audiofile;
        setMode(mode);
        
        audioInputStream = AudioSystem.getAudioInputStream(audiofile);
        
        // Create clip reference
        clip = AudioSystem.getClip();
        
        // Open audioInputStream to the clip
        clip.open(audioInputStream);
        
    }
    
    /**
     * Plays the audio Clip.
     */
    public void play() {
        //start the clip
        switch(mode){
            case loop:
                clip.loop(-1);
            case regular:
                clip.loop(0);
        }
        status = PlaybackStatus.playing;
    }
    
    /**
     * Pauses the audio Clip.
     */
    public void pause() {
        status = PlaybackStatus.paused;
        
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
    }
    
    /**
     * Resumes the playback of the audio Clip.
     */
    public void resumeAudio() {
        if (status != PlaybackStatus.playing) {
            clip.close();
            try{
                resetAudioStream();
            }catch (Exception e) {
                System.out.println("[-] Could not resume playing Clip");
            }
            clip.setMicrosecondPosition(currentFrame);
            this.play();
            
            status = PlaybackStatus.playing;
        }
    }
    
    /**
     * Restarts the audio Clip.
     */
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
        
        status = PlaybackStatus.playing;
    }
    
    /**
     * Stops the audio Clip.
     */
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        status = PlaybackStatus.stopped;
        
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
    
    /**
     * Resets the Clip.
     */
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(audiofile);
        clip.open(audioInputStream);
    }
    
    /**
     * Sets the PlaybackMode of the Clip.
     * @param mode the enum value of the PlaybackMode
     */
    public void setMode(PlaybackMode mode) {
        this.mode = mode;
    }
    
    
    
}