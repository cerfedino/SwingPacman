package Entities;

import AnimationEngine.AnimationManager;
import Game.Game;
import Media.EImage;
import Media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Sprite to be displayed on the game screen.
 */
public class Sprite extends JPanel {
    private int x;
    private int y;
    
    private EImage image;
    
    /**
     * Initializes a Sprite object.
     * @param x the x coordinate of the Sprite
     * @param y the y coordinate of the Sprite
     * @param en the EImage ENUM for the image
     */
    public Sprite(int x, int y, EImage en) {
        if (en == null)
            setImage(EImage.placeholder);
        else
            setImage(en);
        
        setX(x);
        setY(y);
        setOpaque(false);
    
        getInitialAnimationFrame();
    }
    
    /**
     * Paints the sprite in its swing Container.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setImage(AnimationManager.getNextFrame(image));
        g.drawImage(Media.getImg(image), 0, 0, this); // see javadoc for more info on the parameters
    }
    
    /**
     * Handles the complete deletion and removal of this Sprite from the game.
     */
    public void removeSprite() {
        Game.painter().unregisterSprite(this);
    }
    
    /////////////////////////////
    // Getters and setters below
    
    public EImage getImage(){
        return image;
    }
    
    public void setImage(EImage image){
        int x = getSpriteX() ,y = getSpriteY();
        
        BufferedImage img = Media.getImg(image);
        setSize(img.getWidth(), img.getHeight());
        
        setX(x);
        setY(y);
        this.image = image;
    }
    
    
    /**
     * Returns the actual X coordinate based on the Sprite's center.
      * @return the actual X coordinate
     */
    public int getSpriteX(){
        return x + getWidth()/2;
    }
    
    /**
     * Returns the actual Y coordinate based on the Sprite's center.
     * @return the actual Y coordinate
     */
    public int getSpriteY(){
        return y + getWidth()/2;
    }
    
    /**
     * DO NOT USE. Method Override from JPanel in order to make the Sprite centered.
     * @return
     */
    @Override
    public int getX() {
        return x;
    }
    
    /**
     * DO NOT USE. Method Override from JPanel in order to make the Sprite centered.
     * @return
     */
    @Override
    public int getY() {
        return y;
    }
    
    public void setX(int newX){
        x = newX - getWidth()/2;
        setLocation(x,y);
    }
    
    public void setY(int newY){
        y = newY - getWidth()/2;
        setLocation(x,y);
    }
    
    public void getInitialAnimationFrame() {
        setImage(AnimationManager.getFirstFrame(this));
    }
    
}
