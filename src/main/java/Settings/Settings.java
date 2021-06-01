package Settings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.EnumMap;

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
    
        // Game mechanics
        newMapping.put(EParam.pacman_speed,            3.00);
        newMapping.put(EParam.pacman_starting_lives,   3);
    
        newMapping.put(EParam.ghost_speed,             2.00);
        newMapping.put(EParam.vulnerable_mstime,       15000);
        newMapping.put(EParam.ghost_count,             4);
        newMapping.put(EParam.ghost_vuln_speed,        2);
    
        newMapping.put(EParam.food_distancing,        20);
        newMapping.put(EParam.large_food_score,        40);
        newMapping.put(EParam.small_food_score,        20);
    
        newMapping.put(EParam.special_food_spawn_odd,  2);
    
        // Graphics
        newMapping.put(EParam.line_color,              Color.blue);
        newMapping.put(EParam.line_thickness,          12);
        
        newMapping.put(EParam.background_color,        Color.black);
        newMapping.put(EParam.path_width,              30);
        
        newMapping.put(EParam.label_size, 50);
        newMapping.put(EParam.label_color, Color.WHITE);
    
        // Controls
        newMapping.put(EParam.KEY_turn_up,              KeyEvent.VK_UP);
        newMapping.put(EParam.KEY_turn_down,            KeyEvent.VK_DOWN);
    
        newMapping.put(EParam.KEY_turn_left,            KeyEvent.VK_LEFT);
        newMapping.put(EParam.KEY_turn_right,           KeyEvent.VK_RIGHT);
        
        mapping = newMapping;
    }
    
    /**
     * Scales all the graphics settings by a factor.
     * @param scalefactor the scale factor of the new graphic settings
     */
    static public void rescaleSettings(double scalefactor) {
        setDefaultSettings();
        mapping.put(EParam.pacman_speed,    (int)((double)mapping.get(EParam.pacman_speed)*scalefactor));
        mapping.put(EParam.ghost_speed,     (int)((double)mapping.get(EParam.ghost_speed)*scalefactor));
        mapping.put(EParam.food_distancing, (int)((int)mapping.get(EParam.food_distancing)*scalefactor));
        mapping.put(EParam.line_thickness,  (int)((int)mapping.get(EParam.line_thickness)*scalefactor));
        mapping.put(EParam.path_width,      (int)((int)mapping.get(EParam.path_width)*scalefactor));
        mapping.put(EParam.label_size,      (int)((int)mapping.get(EParam.label_size)*scalefactor));
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the settings EnumMap
     */
    public static EnumMap<EParam, Object> getMapping(){
        return mapping;
    }
    
}
