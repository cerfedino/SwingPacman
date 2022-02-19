package AudioEngine;

import Media.EAudio;

import javax.sound.sampled.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles all the audio-playing aspect of the game.
 */
public class AudioEngine {
    
    private static final ArrayList<AudioEntity> entities = new ArrayList<>();
    
    /**
     * Initializes the AudioEngine class.
     */
    public static void initAudioEngine() {
        try {
            entities.add(new AudioEntity(EAudio.ghost_moving, PlaybackMode.loop));
        } catch (Exception e) {
            System.err.println("\n[-] Couldn't init the audio engine.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Creates ad plays an AudioEntity on demand, without keeping track of it.
     * @param audio the audio to play
     * @param callback the callback function to call after the AudioEntity has finished playing
     */
    public static void play(EAudio audio, PlaybackMode mode, FunctionCallback callback) {
        try {
            AudioEntity entity = new AudioEntity(audio, mode);
            entities.add(entity);
            
            entity.getClip().addLineListener(new LineListener() {
                AudioEntity a = entity;
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP && a.status != PlaybackStatus.paused && a.status != PlaybackStatus.stopped) {
                        a.getClip().removeLineListener(this);
                        a.getClip().close();
                        entities.remove(a);
                        if (callback != null) {
                            callback.callback();
                        }
                    }
                }
            });
            entity.play();
        }catch (Exception e) {
            System.err.println("\n[-] Couldn't set up the AudioEntity correctly, calling callback immediately.");
            e.printStackTrace();
            if (callback != null) {
                callback.callback();
            }
        }
    }
    
    /**
     * Plays an audio only if its not playing already.
     * @param audio the audio to play
     * @param callback the callback function to call after the AudioEntity has finished playing
     */
    public static void playIfNotAlready(EAudio audio, PlaybackMode mode, FunctionCallback callback) {
        if (!isPlaying(audio))
            play(audio,mode, callback);
    }
    
    /**
     * Tries to restart an existing SFX, otherwise plays a new one.
     * @param audio the audio to play
     * @param mode the mode to play the audio (e.g Loop or regular)
     * @param callback the callback function to call after the audio has finished playing
     */
    public static void restartOrPlay(EAudio audio, PlaybackMode mode, FunctionCallback callback) {
        for (AudioEntity e : entities) {
            if(e.getAudio() == audio) {
                try{
                    e.restart();
                } catch(IOException ex) {
                    ex.printStackTrace();
                    play(audio, mode, callback);
                } finally{
                    return;
                }
            }
        }
        play(audio, mode, callback);
    }
    
    /**
     * Stops a certain audio sfx from playing.
     * @param audio the audio sfx to stop
     */
    public static void stop(EAudio audio){
        for (AudioEntity e : entities){
            if (e.getAudio() == audio){
                try{
                    e.stop();
                } catch (Exception ex) {
                    System.err.println("\n[-] Couldnt stop AudioEntity");
                    ex.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Pauses all AudioEntities.
     */
    public static void pauseAll() {
        for(AudioEntity e : entities) {
            e.pause();
        }
    }
    
    /**
     * Resumes all AudioEntities.
     */
    public static void resumeALl() {
        for(AudioEntity e : entities) {
            e.resumeAudio();
        }
    }
    
    /**
     * Returns whether an audio sfx is playing.
     * @param audio the audio sfx to check for
     * @return whether it's playing
     */
    public static boolean isPlaying(EAudio audio) {
        for(AudioEntity a : entities) {
            if(a.isPlaying()) {
                return true;
            }
        }
        return false;
    }
}
