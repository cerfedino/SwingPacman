package Settings;

import java.awt.*;
import java.util.EnumMap;
import java.lang.Integer;

/**
 * Class containing all the static settings necessary to run the game.
 */
public class Settings {
    static private EnumMap<EParam, Object> mapping;
     
    /**
     * Initializes the default settings.
     */
    static {
        setDefaultSettings();
    }
    
    /**
     * Returns the integer value associated with a Setting.
     * @param eparam the Setting ENUM key
     * @return the setting's value
     */
    public static Object get(EParam eparam) {
        return mapping.get(eparam);
    }
    
    /**
     * Sets the current settings to their default values.
     */
    static private void setDefaultSettings() {
        EnumMap<EParam, Object> newMapping = new EnumMap<>(EParam.class);
    
        newMapping.put(EParam.pacman_speed,            4);
        newMapping.put(EParam.pacman_starting_lives,   3);
    
        newMapping.put(EParam.ghost_speed,             3);
        newMapping.put(EParam.vulnerable_mstime,       15000);
    
        newMapping.put(EParam.large_food_score,        40);
        newMapping.put(EParam.small_food_score,        20);
    
        newMapping.put(EParam.special_food_spawn_odd,  10);
        
        
        newMapping.put(EParam.line_color,              Color.red);
        newMapping.put(EParam.line_thickness,          5);
        
        newMapping.put(EParam.background_color,        Color.black);
        newMapping.put(EParam.path_width,              20);
        mapping = newMapping;
    }
    
    /**
     * Scales all the graphics settings by a factor.
     * @param scalefactor the scale factor of the new graphic settings
     */
    static public void rescaleGraphicSettings(double scalefactor) {
        setDefaultSettings();
        mapping.put(EParam.line_thickness, (int)((int)mapping.get(EParam.line_thickness)*scalefactor));
        mapping.put(EParam.path_width, (int)((int)mapping.get(EParam.path_width)*scalefactor));
    }
    /**
     * Getter method for the EnumMap.
     * @return the settings EnumMap
     */
    public static EnumMap<EParam, Object> getMapping(){
        return mapping;
    }
    
}
