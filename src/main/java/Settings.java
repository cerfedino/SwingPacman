import java.util.EnumMap;
import java.lang.Integer;

/**
 * Class containing all the static settings necessary to run the game.
 */
public class Settings {
    static private EnumMap<Param, Integer> mapping;
     
    /**
     * Initializes the default settings.
     */
    static {
        setDefaultSettings();
    }
    
    /**
     * Returns the integer value associated with a Setting.
     * @param param the Setting ENUM key
     * @return the setting's value
     */
    public static int get(Param param) {
        return mapping.get(param);
    }
    
    /**
     * Sets the current settings to their default values.
     */
    static private void setDefaultSettings() {
        EnumMap<Param, Integer> newMapping = new EnumMap<>(Param.class);
    
        newMapping.put(Param.pacman_speed,            4);
        newMapping.put(Param.pacman_starting_lives,   3);
    
        newMapping.put(Param.ghost_speed,             3);
        newMapping.put(Param.vulnerable_mstime,       15000);
    
        newMapping.put(Param.large_food_score,        40);
        newMapping.put(Param.small_food_score,        20);
    
        newMapping.put(Param.special_food_spawn_odd,  10);
        
        mapping = newMapping;
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the settings EnumMap
     */
    public static EnumMap<Param, Integer> getMapping(){
        return mapping;
    }
    
}
