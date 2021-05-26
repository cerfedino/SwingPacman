import java.util.EnumMap;

import static org.junit.Assert.*;
import org.junit.Test;

import Media.*;

public class MediaTest {
    /**
     * Checks that every possible image has been imported
     *  and available to use.
     */
    @Test
    public void allImgMapped() {
        EnumMap m = Media.getImgMap();
        
        for(EImage image : EImage.values()) {
            assertTrue(m.containsKey(image));
        }
    }
    
    /**
     * Checks that every possible SFX has been imported
     *  and available to use.
     */
    @Test
    public void allSfxMapped() {
        EnumMap m = Media.getSfxMap();
        
        for(EAudio sfx : EAudio.values()) {
            assertTrue(m.containsKey(sfx));
        }
    }
    
}