package Painter;

import Entities.Sprite;
import Map.Map;
import Media.Media;
import Settings.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Responsible of painting and updating all the graphic elements on screen.
 */
public class Painter {
    
    private static Toolkit t= Toolkit.getDefaultToolkit();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    
    private JLayeredPane gamepanel;
    private JFrame gameframe;
    
    
    /**
     * Initializes the Painter object by creating and setting the game window.
     */
    public Painter() {
        Dimension screenSize = t.getScreenSize();
        int size = Math.min(screenSize.height, screenSize.width);
        
        // Scales the Entities.Sprite images and graphic settings based on the screen size
        Scaler.setNewsize(size);
        Media.rescaleMedia(Scaler.getScale_factor());
        Settings.rescaleGraphicSettings(Scaler.getScale_factor());
        
        gameframe = new JFrame("SwingPacman");
        gameframe.setSize(size, size);
        gameframe.setResizable(false);
        gameframe.getContentPane().setBackground((Color)Settings.get(EParam.background_color));
        gameframe.setUndecorated(true);
        
        gameframe.setLayout(null);
        gameframe.setVisible(true);
        
        gamepanel = new JLayeredPane();
        gamepanel.setBounds(0,0,size,size);
        gameframe.add(gamepanel);
    
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Initializes a Entities.Sprite on the game screen by adding it
     *  to the game frame.
     * @param sprite the sprite to initialize
     */
    public void registerSprite(Sprite sprite) {
        if ( !sprites.contains(sprite)) {
            sprites.add(sprite);
            gamepanel.add(sprite,1);
            gamepanel.revalidate();
            gamepanel.repaint();
        }
    }
    
    /**
     * Removes a sprite from the game frame.
     * @param sprite the sprite to unregister/delete
     */
    public void unregisterSprite(Sprite sprite) {
        if ( sprites.contains(sprite)) {
            sprites.remove(sprite);
            gamepanel.remove(sprite);
            gamepanel.repaint();
        }
    }
    
    /**
     * Add the map to the game panel to be displayed
     * @param map the sprite to initialize
     */
    public void registerMap(Map map) {
        gamepanel.add(map,10);
        map.repaint();
        gamepanel.revalidate();
        gamepanel.repaint();
    }
    
    /**
     * Returns whether the Sprite is registered in the game screen.
     * @param sprite the sprite to check for
     * @return whether the Sprite is registered in the game screen
     */
    public boolean isSpriteRegistered(Sprite sprite) {
        return sprites.contains(sprite);
    }
    
    
    ///////////////////
    // Getters and Setters below
    
    
}
