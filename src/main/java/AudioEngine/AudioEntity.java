package AudioEngine;

import Media.EAudio;
import Media.Media;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents the state in which an AudioEntity can be.
 */
enum PlaybackStatus {
    playing,paused,stopped
}

/**
 * An AudioEntity that plays a certain SFX Clip, complete with independent controls for pausing, stopping restarting ecc.
 */
public class AudioEntity {
    
    // To store current position
    Long currentFrame;
    
    EAudio audio;
    InputStream audiofile;
    Clip clip;
    
    // current status of clip
    PlaybackStatus status = PlaybackStatus.stopped;
    PlaybackMode mode = PlaybackMode.regular;
    
    AudioInputStream audioInputStream;
    
    /**
     * Initializes an AudioEntity object.
     * @param audio the SFX audio associated with this AudioEntity.
     * @param mode the mode to play this AudioEntity at (eg. loop, regular ecc)
     */
    public AudioEntity(EAudio audio, PlaybackMode mode) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Create AudioInputStream object
        this.audio = audio;
        this.audiofile = Media.getSfx(audio);
        setMode(mode);
        status = PlaybackStatus.stopped;
        
        audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audiofile));
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
        switch(mode) {
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
        status = status != PlaybackStatus.stopped ? PlaybackStatus.paused : status;
        
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
    }
    
    /**
     * Resumes the playback of the audio Clip.
     */
    public void resumeAudio() {
        if (status == PlaybackStatus.paused) {
            clip.close();
            try{
                resetAudioStream();
            }catch (Exception e) {
                System.out.println("[-] Could not play audio clip");
                e.printStackTrace();
            }
            clip.setMicrosecondPosition(currentFrame);
            this.play();
            
            status = PlaybackStatus.playing;
        }
    }
    
    /**
     * Restarts the audio Clip.
     */
    public void restart() throws IOException{
        pause();
        
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        audiofile.reset();
        this.play();
        
        status = PlaybackStatus.playing;
    }
    
    /**
     * Stops the audio Clip.
     */
    public void stop() {
        status = PlaybackStatus.stopped;
        
        clip.stop();
        currentFrame = 0L;
    }
    
    /**
     * Resets the Clip.
     */
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        
        audiofile.reset();
        audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audiofile));
        System.out.println(audio + "     reset");
        
        clip.open(audioInputStream);
    }
    
    /**
     * Sets the PlaybackMode of the Clip.
     * @param mode the enum value of the PlaybackMode
     */
    public void setMode(PlaybackMode mode) {
        this.mode = mode;
    }
    
    //////////////////
    // Getters and setters below
    
    public Clip getClip(){
        return clip;
    }
    
    public EAudio getAudio(){
        return audio;
    }
    
    public boolean isPlaying() {
        return status == PlaybackStatus.playing;
    }
}