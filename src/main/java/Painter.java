import Media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Responsible of painting all the elements on screen that need to be repainted.
 */
public class Painter {
    
    private static Toolkit t= Toolkit.getDefaultToolkit();
    private HashMap<Sprite, JLabel> spriteMapping = new HashMap<>();
    
    private JFrame gameframe;
    
    public Painter() {
        Dimension screenSize = t.getScreenSize();
        int size = Math.min(screenSize.height, screenSize.width);
        
        gameframe = new JFrame("Picku mater");
        gameframe.setSize(size, size);
        gameframe.setResizable(true);
        gameframe.getContentPane().setBackground(Color.black);
        
        gameframe.setLayout(null);
        gameframe.setVisible(true);
        
        gameframe.revalidate();
        gameframe.repaint();
    }
    
    /**
     * Repaints a Sprite on the game screen
     * @param sprite the sprite to repaint
     */
    public void repaintSprite(Sprite sprite) {
        // TODO: Make it way more efficient by not having it reset the image and everything
        //TODO: WHY THE FUCK ARE WE USING JLABELS.
        
        if (spriteMapping.containsKey(sprite)) {
            JLabel j = spriteMapping.get(sprite);
            
            int width = j.getIcon().getIconWidth();
            int height = j.getIcon().getIconHeight();

            int x =  sprite.getX() - (width/2);
            int y =  sprite.getY() - (height/2);

            Icon i = j.getIcon();
    
            j.setBounds(sprite.getX(), sprite.getY(), i.getIconWidth(), i.getIconHeight());
            
            j.setBackground(Color.blue);
            j.setIcon(Media.get(sprite.getImage()));
        }
        
    }
    
    /**
     * Initializes a Sprite on the game screen by creating
     *  a corrisponding Swing image and adding it to the HashMap.
     * @param sprite the spite to initialize
     */
    public void registerSprite(Sprite sprite) {
        if ( !spriteMapping.containsKey(sprite)) {
            JLabel j = new JLabel(Media.get(sprite.getImage()));
            spriteMapping.put(sprite, j);
            gameframe.add(j);
            repaintSprite(sprite);
        }
    }
    
    
    ///////////////////
    // Getters and Setters below
    
    
}
