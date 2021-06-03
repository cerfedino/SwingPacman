package AudioEngine;

import Media.EAudio;
import Media.Media;

import javax.sound.sampled.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

public class AudioEngine {
    
    private static final ArrayList<AudioEntity> entities = new ArrayList<AudioEntity>();
    
    /**
     * Initializes the AudioEngine class.
     */
    public static void initAudioEngine() {
        try {
            entities.add(new AudioEntity(EAudio.ghost_moving, PlaybackMode.loop));
        
        } catch (Exception e) {
            System.out.println("[-] Couldn't set up the AudioEntities correctly.");
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
            
            if (audio == EAudio.round_start) {
                System.out.println("AYOO");
            }
            
            entity.getClip().addLineListener(new LineListener() {
                
                AudioEntity a = entity;
                @Override
                public void update(LineEvent event) {
                    if (a.getAudio() == EAudio.round_start) {
                        System.out.println("SSSSSS");
                    }
                    
                    if (event.getType() == LineEvent.Type.STOP && a.status != PlaybackStatus.paused) {
                        
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
            System.out.println("[-] Couldn't set up the AudioEntity correctly.");
            e.printStackTrace();
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
    
    public static void restartOrPlay(EAudio audio, PlaybackMode mode, FunctionCallback callback) {
        for (AudioEntity e : entities) {
            if(e.getAudio() == audio) {
                e.restart();
                return;
            }
        }
        play(audio, mode, callback);
    }
    
    public static void stop(EAudio audio){
        ArrayList<AudioEntity> entitiesToRemove = new ArrayList<>();
        for (AudioEntity e : entities){
            if (e.getAudio() == audio){
                try{
                    e.stop();
                    entitiesToRemove.add(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        for (AudioEntity e : entitiesToRemove) {
            entities.remove(e);
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
    
    public static boolean isPlaying(EAudio audio) {
        for(AudioEntity a : entities) {
            if(a.isPlaying()) {
                return true;
            }
        }
        return false;
    }
}
