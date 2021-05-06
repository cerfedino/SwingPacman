import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;

public class SettingsTest {
    
    
    /**
     * Checks that every possible parameter is mapped in the EnumMap.
     */
    @Test
    public void allSettingsMapped() {
        EnumMap p = Settings.getMapping();
        
        for(Param param : Param.values()) {
            assertTrue(p.containsKey(param));
        }
    }
    
}