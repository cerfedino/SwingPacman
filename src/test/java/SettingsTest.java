import static org.junit.Assert.*;
import Settings.*;

import org.junit.Test;

import java.util.EnumMap;

public class SettingsTest {
    
    
    /**
     * Checks that every possible parameter is mapped in the EnumMap.
     */
    @Test
    public void allSettingsMapped() {
        EnumMap p = Settings.getMapping();
        
        for(EParam param : EParam.values()) {
            assertTrue(p.containsKey(param));
        }
    }
    
}