package Media;

import java.util.EnumMap;

/**
 *  Contains all the media files needed to display the game.
 */
public class Media {
    //TODO: Figure out whats the type of an image file
    static private EnumMap<EImage, Integer> mapping;
    
    /**
     * Imports the media files.
     */
    static {
        importMedia();
    }
    
    /**
     * Returns the image object associated with its key.
     * @param eimage the EImage ENUM key
     * @return the image
     */
    public static int get(EImage eimage) {
        return mapping.get(eimage);
    }
    
    /**
     * Imports the media files in the class.
     */
    static private void importMedia() {
        //TODO: Actually implement the media importing aspect
        
        /*
        EnumMap<EImage, Integer> newMapping = new EnumMap<>(EImage.class);

        newMapping.put(EImage.pacman,            4);

        newMapping.put(EImage.ghost,   3);
        newMapping.put(EImage.ghost_vuln,             3);

        newMapping.put(EImage.large_food,        40);
        newMapping.put(EImage.small_food,        20);

        newMapping.put(EImage.hearth,  10);

        mapping = newMapping;*/
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the settings EnumMap
     */
    public static EnumMap<EImage, Integer> getMapping() {
        return mapping;
    }
    
}
