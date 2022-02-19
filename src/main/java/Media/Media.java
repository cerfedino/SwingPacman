package Media;

import AnimationEngine.AnimationManager;
import AudioEngine.AudioEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;


/**
 *  Contains all the media files needed to display the game. Scales them based on the screen size,
 *      making sure the game runs and looks the same on every screen size.
 */
public class Media {
    static private EnumMap<EImage, BufferedImage> img;
    static private EnumMap<EAudio, String> sfx;
    static private EnumMap<EFont, Font> font;
    
    /**
     * Imports the media files.
     * Initializes the animations.
     * Initializes the AudioEngine.
     *  Throws an Exception if a media file can't be found
     */
    static {
        try{
            importMedia();
        } catch (IOException e){
            System.out.println("[-] Could not import media resources.");
            e.printStackTrace();
        }
        AnimationManager.initAnimations();
        AudioEngine.initAudioEngine();
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
     * Returns the sfx File object associated with its key.
     * @param eaudio the EAudio ENUM key
     * @return the sfx File
     */
    public static InputStream getSfx(EAudio eaudio) {
        return getStreamfromResource(sfx.get(eaudio));
    }
    
    /**
     * Returns the Font object associated with its key.
     * @param efont the EFont ENUM key
     * @return the Font object
     */
    public static Font getFont(EFont efont) {
        return font.get(efont);
    }
    
    
    /**
     * Imports the media files in the class.
     */
    static private void importMedia() throws IOException  {
        
        EnumMap<EImage, BufferedImage> newImage = new EnumMap<>(EImage.class);
        EnumMap<EAudio, String> newSfx = new EnumMap<>(EAudio.class);
        EnumMap<EFont, Font> newFont = new EnumMap<>(EFont.class);
        
        
        double baseScale = 0.15;
        try {
            newImage.put(EImage.placeholder, scaleImg(ImageIO.read(getStreamfromResource("res/img/placeholder.png")), baseScale));
        
            //////////////////////////////
            
            newImage.put(EImage.pacman_left_1, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_left_1.png")), baseScale));
            newImage.put(EImage.pacman_right_1, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_right_1.png")), baseScale));
            newImage.put(EImage.pacman_up_1, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_up_1.png")), baseScale));
            newImage.put(EImage.pacman_down_1, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_down_1.png")), baseScale));
        
            newImage.put(EImage.pacman_left_2, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_left_2.png")), baseScale));
            newImage.put(EImage.pacman_right_2, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_right_2.png")), baseScale));
            newImage.put(EImage.pacman_up_2, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_up_2.png")), baseScale));
            newImage.put(EImage.pacman_down_2, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_down_2.png")), baseScale));
        
            newImage.put(EImage.pacman_left_3, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_left_3.png")), baseScale));
            newImage.put(EImage.pacman_right_3, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_right_3.png")), baseScale));
            newImage.put(EImage.pacman_up_3, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_up_3.png")), baseScale));
            newImage.put(EImage.pacman_down_3, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_down_3.png")), baseScale));
        
            newImage.put(EImage.pacman_left_4, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_left_4.png")), baseScale));
            newImage.put(EImage.pacman_right_4, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_right_4.png")), baseScale));
            newImage.put(EImage.pacman_up_4, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_up_4.png")), baseScale));
            newImage.put(EImage.pacman_down_4, scaleImg(ImageIO.read(getStreamfromResource("res/img/pacman/pacman_down_4.png")), baseScale));
            
            /////////////////////////////
        
            newImage.put(EImage.ghost1_left, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost1_left.png")), baseScale));
            newImage.put(EImage.ghost1_right, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost1_right.png")), baseScale));
            newImage.put(EImage.ghost1_up, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost1_up.png")), baseScale));
            newImage.put(EImage.ghost1_down, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost1_down.png")), baseScale));
            
        
            newImage.put(EImage.ghost2_left, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost2_left.png")), baseScale));
            newImage.put(EImage.ghost2_right, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost2_right.png")), baseScale));
            newImage.put(EImage.ghost2_up, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost2_up.png")), baseScale));
            newImage.put(EImage.ghost2_down, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost2_down.png")), baseScale));
            
            
            newImage.put(EImage.ghost3_left, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost3_left.png")), baseScale));
            newImage.put(EImage.ghost3_right, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost3_right.png")), baseScale));
            newImage.put(EImage.ghost3_up, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost3_up.png")), baseScale));
            newImage.put(EImage.ghost3_down, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost3_down.png")), baseScale));
            
            
            newImage.put(EImage.ghost4_left, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost4_left.png")), baseScale));
            newImage.put(EImage.ghost4_right, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost4_right.png")), baseScale));
            newImage.put(EImage.ghost4_up, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost4_up.png")), baseScale));
            newImage.put(EImage.ghost4_down, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost4_down.png")), baseScale));
            
        
            newImage.put(EImage.ghost_vuln, scaleImg(ImageIO.read(getStreamfromResource("res/img/ghost/ghost_vuln.png")), baseScale));
    
            newImage.put(EImage.large_food, scaleImg(ImageIO.read(getStreamfromResource("res/img/large_food.png")), baseScale));
            newImage.put(EImage.small_food, scaleImg(ImageIO.read(getStreamfromResource("res/img/small_food.png")), baseScale));
    
            newImage.put(EImage.live, scaleImg(ImageIO.read(getStreamfromResource("res/img/live.png")), baseScale));
            
        } catch(Exception e) {
            System.err.println("Could not import image files");
            e.printStackTrace();
        }
        
        ////////////////////
        // Audio
        
        try {
            newSfx.put(EAudio.placeholder, "res/sfx/ding.wav");
        
            newSfx.put(EAudio.ost, "res/sfx/ost.wav");
            newSfx.put(EAudio.button_click, "res/sfx/button_click.wav");
            
            newSfx.put(EAudio.ghost_moving, "res/sfx/ghost_moving.wav");
            newSfx.put(EAudio.small_food, "res/sfx/small_food.wav");
            newSfx.put(EAudio.large_food, "res/sfx/large_food.wav");
            newSfx.put(EAudio.ghost_vulnerable, "res/sfx/ghost_vulnerable.wav");
            newSfx.put(EAudio.ghost_vulnerable_end, "res/sfx/ghost_vulnerable_end.wav");
            newSfx.put(EAudio.ghost_ate, "res/sfx/ghost_ate.wav");
            newSfx.put(EAudio.round_start, "res/sfx/round_start.wav");
            newSfx.put(EAudio.death_sound, "res/sfx/death_sound.wav");
            
        } catch(Exception e) {
            System.err.println("Could not import audio files");
            e.printStackTrace();
        }
        
        ////////////////////
        // Font
        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT, getStreamfromResource("res/font/game_font1.ttf")).deriveFont(12f);
            newFont.put(EFont.regular, f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(f);
            
        } catch (Exception e) {
            System.err.println("Could not import font files");
            e.printStackTrace();
        }
    
        img  = newImage;
        sfx  = newSfx;
        font = newFont;
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
            BufferedImage originalImage = getImg(img);
            Media.img.replace(img, scaleImg(originalImage,scale));
        }
        AnimationManager.initAnimations();
    }
    
    /**
     * Scales one specific BufferedImage by a certain scale.
     * @param img the BufferedImage to scale
     * @param scale the scale to scale the BufferedImage to
     * @return the scaled BufferedImage
     */
    private static BufferedImage scaleImg(BufferedImage img, double scale) {
        int targetWidth =  (int)(img.getWidth()*scale);
        int targetHeight = (int)(img.getHeight()*scale);
    
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(img, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        
        return resizedImage;
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the img EnumMap
     */
    public static EnumMap<EImage, BufferedImage> getImgMap() {
        return img;
    }
    
    /**
     * Getter method for the EnumMap.
     * @return the sfx EnumMap
     */
    public static EnumMap<EAudio, String> getSfxMap() {
        return sfx;
    }
    
    private static InputStream getStreamfromResource(String resource) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }
    
}
