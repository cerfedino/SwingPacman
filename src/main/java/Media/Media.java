package Media;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *  Contains all the media files needed to display the game.
 */
public class Media {
    static private EnumMap<EImage, ImageIcon> mapping;
    
    /**
     * Imports the media files.
     */
    static {
        try{
            importMedia();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the image object associated with its key.
     * @param eimage the EImage ENUM key
     * @return the image
     */
    public static ImageIcon get(EImage eimage) {
        return mapping.get(eimage);
    }
    
    /**
     * Imports the media files in the class.
     */
    static private void importMedia() throws IOException  {
        
        EnumMap<EImage, ImageIcon> newMapping = new EnumMap<>(EImage.class);
        
        newMapping.put(EImage.pacman, new ImageIcon(ImageIO.read(new File("./src/main/resources/placeholder.png"))));

        newMapping.put(EImage.ghost, new ImageIcon(ImageIO.read(new File("./src/main/resources/placeholder2.png"))));
        newMapping.put(EImage.ghost_vuln, new ImageIcon(ImageIO.read(new File("./src/main/resources/placeholder.png"))));

        newMapping.put(EImage.large_food, new ImageIcon(ImageIO.read(new File("./src/main/resources/placeholder.png"))));
        newMapping.put(EImage.small_food, new ImageIcon(ImageIO.read(new File("./src/main/resources/placeholder.png"))));

        newMapping.put(EImage.hearth, new ImageIcon(ImageIO.read(new File("./src/main/resources/placeholder.png"))));
        
        mapping = newMapping;
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the media EnumMap
     */
    public static EnumMap<EImage, ImageIcon> getMapping() {
        return mapping;
    }
    
}
