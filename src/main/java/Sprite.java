import Media.EImage;

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
     * Paints the sprite in its swing Container.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(Media.Media.get(image), 0, 0, this); // see javadoc for more info on the parameters
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Handles the complete deletion and removal of this Sprite in the game.
     */
    public void removeSprite() {
        Game.painter().unregisterSprite(this);
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
        setSize(img.getWidth(), img.getHeight());
        this.image = image;
    }
    
    public void setX(int newX){
        this.x = newX + x_render_offset;
        
        setLocation(x,y);
    }
    
    public void setY(int newY){
        this.y = newY + y_render_offset;
        
        setLocation(x,y);
    }
    
    public void setPos(int newX, int newY) {
        x = newX + x_render_offset;
        y = newY + y_render_offset;
        
        setLocation(x,y);
    }
    
}
