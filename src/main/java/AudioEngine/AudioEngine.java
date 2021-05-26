package AudioEngine;

import Media.EAudio;
import Media.Media;

import java.util.EnumMap;

public class AudioEngine {
    
    private final EnumMap<EAudio,AudioEntity> entities;
    
    /**
     * Initializes the AudioEngine object.
     */
    public AudioEngine() {
        entities = new EnumMap<>(EAudio.class);
        
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
     */
    public void playOnce(EAudio audio) {
        try{
            new AudioEntity(Media.getSfx(audio), PlaybackMode.regular).play();
        }catch (Exception e) {
            System.out.println("[-] Couldn't set up the AudioEntities correctly.");
            e.printStackTrace();
        }
    }
}
