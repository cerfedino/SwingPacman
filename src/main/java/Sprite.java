import Media.EImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Sprite to be displayed on the game screen.
 */
public class Sprite extends Canvas {
    private int x;
    private int y;
    
    private EImage image;
    
    /**
     * The x and y axis offsets in order for the anchor point
     *  to be the center of the sprite at coordinates (0,0)
     */
    private int x_render_offset;
    private int y_render_offset;
    
    
    /**
     * Initializes a Sprite object.
     * @param x the x coordinate of the Sprite
     * @param y the y coordinate of the Sprite
     * @param en the EImage ENUM for the image
     */
    public Sprite(int x, int y, EImage en) {
        setImage(en);
        setPos(x,y);
    }
    
    /**
     * Paints the sprite in its awt/swing Container.
     */
    @Override
    // TODO: Make the paint method paint with a transparent background
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Media.Media.get(image), 0, 0, this); // see javadoc for more info on the parameters
    }
    
    
    
    /////////////////////////////
    // Getters and setters below
    
    public int getX(){
        return x - x_render_offset;
    }
    
    public int getY(){
        return y - y_render_offset;
    }
    
    public void setImage(EImage image){
        BufferedImage img = Media.Media.get(image);
        x_render_offset = - img.getWidth()/2;
        y_render_offset = - img.getHeight()/2;
        this.image = image;
    }
    
    public void setX(int newX){
        this.x = newX + x_render_offset;
        
        BufferedImage img = Media.Media.get(image);
        setBounds(x,y,img.getWidth(), img.getHeight());
    }
    
    public void setY(int newY){
        this.y = newY + y_render_offset;
        
        BufferedImage img = Media.Media.get(image);
        setBounds(x,y,img.getWidth(), img.getHeight());
    }
    
    public void setPos(int newX, int newY) {
        this.x = newX + x_render_offset;
        this.y = newY + y_render_offset;
    
        BufferedImage img = Media.Media.get(image);
        setBounds(x,y,img.getWidth(), img.getHeight());
    }
    
}
