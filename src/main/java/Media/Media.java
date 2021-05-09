package Media;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *  Contains all the media files needed to display the game.
 */
public class Media {
    static private EnumMap<EImage, BufferedImage> mapping;
    
    /**
     * Imports the media files.
     *  Throws an Exception if a media file can't be found
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
    public static BufferedImage get(EImage eimage) {
        return mapping.get(eimage);
    }
    
    /**
     * Imports the media files in the class.
     */
    static private void importMedia() throws IOException  {
        
        EnumMap<EImage, BufferedImage> newMapping = new EnumMap<>(EImage.class);
        
        newMapping.put(EImage.pacman, ImageIO.read(new File("./src/main/resources/pacman.png")));
    
        newMapping.put(EImage.ghost1, ImageIO.read(new File("./src/main/resources/ghost1.png")));
        newMapping.put(EImage.ghost2, ImageIO.read(new File("./src/main/resources/ghost2.png")));
        newMapping.put(EImage.ghost3, ImageIO.read(new File("./src/main/resources/ghost3.png")));
        newMapping.put(EImage.ghost4, ImageIO.read(new File("./src/main/resources/ghost4.png")));
        newMapping.put(EImage.ghost_vuln, ImageIO.read(new File("./src/main/resources/ghost_vuln.png")));

        newMapping.put(EImage.large_food, ImageIO.read(new File("./src/main/resources/pacman.png")));
        newMapping.put(EImage.small_food, ImageIO.read(new File("./src/main/resources/pacman.png")));

        newMapping.put(EImage.hearth, ImageIO.read(new File("./src/main/resources/pacman.png")));
        
        mapping = newMapping;
    }
    
    /**
     * Scales all the images by a factor.
     * @param scale the scale factor of the new images
     */
    static public void rescaleMedia(double scale) {
        try{
            importMedia();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        for (EImage img: mapping.keySet()) {
            BufferedImage originalImage = mapping.get(img);
            int targetWidth = (int)(originalImage.getWidth()*scale);
            int targetHeight = (int) (originalImage.getHeight()*scale);
            
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            mapping.replace(img, resizedImage);
        }
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the media EnumMap
     */
    public static EnumMap<EImage, BufferedImage> getMapping() {
        return mapping;
    }
    
}
