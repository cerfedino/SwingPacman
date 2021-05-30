package AudioEngine;

import Media.EAudio;
import Media.Media;

import javax.sound.sampled.LineEvent;import javax.sound.sampled.Clip;

import javax.sound.sampled.LineListener;

import java.util.ArrayList;
import java.util.EnumMap;

public class AudioEngine {
    
    private final EnumMap<EAudio,AudioEntity> entities;
    private final ArrayList<AudioEntity> tempEntities;
    /**
     * Initializes the AudioEngine object.
     */
    public AudioEngine() {
        entities = new EnumMap<>(EAudio.class);
        tempEntities = new ArrayList<AudioEntity>();
        
        try {
            entities.put(EAudio.ghost_moving, new AudioEntity(Media.getSfx(EAudio.ghost_moving), PlaybackMode.loop));
        } catch (Exception e) {
            System.out.println("[-] Couldn't set up the AudioEntities correctly.");
            e.printStackTrace();
        }
    }
    
    /**
     * Sets the playback mode of a Clip.
     * @param eaudio the audio of the AudioEntity to modify
     * @param mode the PlayBackMode to set
     */
    public void setPlaybackMode(EAudio eaudio, PlaybackMode mode) {
        if (entities.containsKey(eaudio)) {
            entities.get(eaudio);
        }
    }
    
    /**
     * Creates ad plays an AudioEntity on demand, without keeping track of it.
     * @param audio the audio to play
     * @param callback the callback function to call after the AudioEntity has finished playing
     */
    public void playOnce(EAudio audio, FunctionCallback callback) {
        try{
            AudioEntity a = new AudioEntity(Media.getSfx(audio), PlaybackMode.regular);
            tempEntities.add(a);
            
            if (callback != null) {
                Clip c = a.getClip();
                c.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event){
                        if (event.getType() == LineEvent.Type.STOP && a.status != PlaybackStatus.paused) {
                            c.removeLineListener(this);
                            tempEntities.remove(a);
                            callback.callback();
                        }
                    }
                });
            }
            a.play();
        }catch (Exception e) {
            System.out.println("[-] Couldn't set up the AudioEntity correctly.");
            e.printStackTrace();
        }
    }
    
    /**
     * Pauses all AudioEntities.
     */
    public void pauseAll() {
        for(AudioEntity e : entities.values()) {
            e.pause();
        }
        for(AudioEntity e : tempEntities) {
            e.pause();
        }
    }
    
    /**
     * Resumes all AudioEntities.
     */
    public void resumeALl() {
        for(AudioEntity e : entities.values()) {
            e.resumeAudio();
        }
        for(AudioEntity e : tempEntities) {
            e.resumeAudio();
        }
    }
}
