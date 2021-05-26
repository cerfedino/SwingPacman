import java.util.EnumMap;

import static org.junit.Assert.*;
import org.junit.Test;

import Media.*;

public class MediaTest {
    /**
     * Checks that every possible media has been imported
     *  and available to use.
     */
    @Test
    public void allMediaMapped() {
        EnumMap m = Media.getImgMap();
        
        for(EImage image : EImage.values()) {
            assertTrue(m.containsKey(image));
        }
    }
    
}