package Media;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;

/**
 *  Contains all the media files needed to display the game.
 */
public class Media {
    static private EnumMap<EImage, BufferedImage> img;
    static private EnumMap<EAudio, File> sfx;
    
    /**
     * Imports the media files.
     *  Throws an Exception if a media file can't be found
     */
    static {
        try{
            importMedia();
        } catch (IOException e){
            System.out.println("[-] Could not import media resources.");
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the image object associated with its key.
     * @param eimage the EImage ENUM key
     * @return the image
     */
    public static BufferedImage getImg(EImage eimage) {
        return img.get(eimage);
    }
    
    /**
     * Returns the File object associated with its key.
     * @param eaudio the EAudio ENUM key
     * @return the sfx File
     */
    public static File getSfx(EAudio eaudio) {
        return sfx.get(eaudio);
    }
    
    /**
     * Imports the media files in the class.
     */
    static private void importMedia() throws IOException  {
    
        EnumMap<EImage, BufferedImage> newImage = new EnumMap<>(EImage.class);
        EnumMap<EAudio, File> newSfx = new EnumMap<>(EAudio.class);
    
        newImage.put(EImage.pacman, ImageIO.read(new File("./src/main/resources/img/pacman.png")));
    
        newImage.put(EImage.ghost1, ImageIO.read(new File("./src/main/resources/img/ghost1.png")));
        newImage.put(EImage.ghost2, ImageIO.read(new File("./src/main/resources/img/ghost2.png")));
        newImage.put(EImage.ghost3, ImageIO.read(new File("./src/main/resources/img/ghost3.png")));
        newImage.put(EImage.ghost4, ImageIO.read(new File("./src/main/resources/img/ghost4.png")));
        newImage.put(EImage.ghost_vuln, ImageIO.read(new File("./src/main/resources/img/ghost_vuln.png")));

        newImage.put(EImage.large_food, ImageIO.read(new File("./src/main/resources/img/pacman.png")));
        newImage.put(EImage.small_food, ImageIO.read(new File("./src/main/resources/img/pacman.png")));

        newImage.put(EImage.hearth, ImageIO.read(new File("./src/main/resources/img/pacman.png")));
        
        ////////////////////
        // Audio
        newSfx.put(EAudio.ghost_moving, new File("./src/main/resources/sfx/ghost_moving.wav").getAbsoluteFile());
        newSfx.put(EAudio.ghost_vulnerable, new File("./src/main/resources/sfx/placeholder.wav").getAbsoluteFile());
        newSfx.put(EAudio.pacman_eating, new File("./src/main/resources/sfx/placeholder.wav").getAbsoluteFile());
        newSfx.put(EAudio.ghost_vulnerable_end, new File("./src/main/resources/sfx/placeholder.wav").getAbsoluteFile());
    
        img= newImage;
        sfx = newSfx;
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
        
        for (EImage img: img.keySet()) {
            BufferedImage originalImage = Media.img.get(img);
            int targetWidth = (int)(originalImage.getWidth()*scale);
            int targetHeight = (int) (originalImage.getHeight()*scale);
            
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            Media.img.replace(img, resizedImage);
        }
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the media EnumMap
     */
    public static EnumMap<EImage, BufferedImage> getImgMap() {
        return img;
    }
    
}
