import java.awt.*;
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
    
    /**
     * Checks the types of the parameters.
     */
    @Test
    public void settingsTypeCheck() {
        EnumMap p = Settings.getMapping();
        
        for(EParam param : EParam.values()) {
            switch(param) {
                case background_color:
                case line_color:
                    //TODO: FInd a way to assert the class of an object
                    //assertEquals(Color.class, p.get(param).getClass());
                    break;
                default:
                    //assertEquals(Integer.class, p.get(param).getClass());
            }
        }
    }
    
}