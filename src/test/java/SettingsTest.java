import java.util.EnumMap;

import static org.junit.Assert.*;
import org.junit.Test;

import Settings.*;

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