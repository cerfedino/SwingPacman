package Painter;

import Entities.Sprite;
import Game.GameInputManager;
import Map.Map;
import Media.*;
import Painter.HUD.LivesJPanel;
import Painter.HUD.RoundJLabel;
import Painter.HUD.ScoreJLabel;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Responsible of painting and updating all the on-screen graphic elements.
 */
public class Painter {
    
    private static Toolkit t= Toolkit.getDefaultToolkit();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    
    private JLayeredPane gamepanel;
    private JFrame gameframe;
    
    private ScoreJLabel scoreHUD;
    private LivesJPanel livesHUD;
    private static RoundJLabel roundHUD;
    
    /**
     * Initializes the Painter object by creating and setting the game window.
     * @param g the input listener for the users inputs
     */
    public Painter(GameInputManager g) {
        Dimension screenSize = t.getScreenSize();
        int size = Math.min(screenSize.height, screenSize.width);
    
        System.out.println("size: " + size);
        System.out.println("DPI: " + t.getScreenResolution());
    
    
        // Scales the Sprite images and graphic settings based on the screen size
        Scaler.setNewsize(size);
        Scaler.setNewsize(size-Scaler.scale(100));
        Media.rescaleMedia(Scaler.getScale_factor());
        Settings.rescaleSettings(Scaler.getScale_factor());
        
        gameframe = new JFrame("SwingPacman");
        gameframe.setIconImage(Media.getImg(EImage.pacman_right_1));
        
        gameframe.setSize(Scaler.getNewSize(), size);
        gameframe.setResizable(false);
        gameframe.getContentPane().setBackground((Color)Settings.get(EParam.background_color));
        gameframe.setUndecorated(true);
        
        gameframe.setLayout(null);
        gameframe.setVisible(true);
        
        gamepanel = new JLayeredPane();
        gamepanel.setBounds(0,0,size,size);
        
        
        gameframe.add(gamepanel);
    
        scoreHUD= new ScoreJLabel();
        scoreHUD.setBounds(0,size-Scaler.scale(100), Scaler.scale(500), (int)Settings.get(EParam.label_size));
        
        livesHUD = new LivesJPanel((int)Settings.get(EParam.pacman_starting_lives));
        livesHUD.setBounds(size-Scaler.scale(600),size-Scaler.scale(100), Scaler.scale(600), Scaler.scale(100));
        
        roundHUD = new RoundJLabel();
        roundHUD.setLocation(Scaler.scale(400), Scaler.scale(470));

        gameframe.add(scoreHUD);
        gameframe.add(livesHUD);
        gameframe.add(roundHUD);
        
        gamepanel.setFocusable(true);
        gamepanel.grabFocus();
        gamepanel.addKeyListener(g);
        
        gamepanel.revalidate();
        gamepanel.repaint();
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Initializes a Sprite on the game screen by adding it to the game panel.
     *  to the game frame.
     * @param sprite the sprite to initialize
     */
    public void registerSprite(Sprite sprite) {
        if ( !sprites.contains(sprite)) {
            sprites.add(sprite);
            gamepanel.add(sprite,1);
        }
    }
    
    /**
     * Removes a sprite from the game panel.
     * @param sprite the sprite to unregister/delete
     */
    public void unregisterSprite(Sprite sprite) {
        if ( sprites.contains(sprite)) {
            sprites.remove(sprite);
            gamepanel.remove(sprite);
        }
    }
    
    /**
     * Add the map to the game panel to be displayed
     * @param map the sprite to initialize
     */
    public void registerMap(Map map) {
        gamepanel.add(map,-1);
        gamepanel.revalidate();
        gamepanel.repaint();
    }
    
    /**
     * Returns whether the Sprite is registered in the game panel.
     * @param sprite the sprite to check for
     * @return whether the Sprite is registered in the game panel
     */
    public boolean isSpriteRegistered(Sprite sprite) {
        return sprites.contains(sprite);
    }
    
    ///////////////////
    // Getters and Setters below
    public void updateScoreLabel(long newScore) {
        scoreHUD.updateScore(newScore);
    }
    public void updateLivesPanel(int newLives) { livesHUD.updateLives(newLives);}
    public void updateRoundPanel(int newRound) { roundHUD.updateRound(newRound);}
    public static RoundJLabel getRoundHUD() { return roundHUD; }
    
    public JLayeredPane getGamepanel(){
        return gamepanel;
    }
    
    public JFrame getGameframe(){
        return gameframe;
    }
}
