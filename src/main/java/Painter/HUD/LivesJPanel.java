package Painter.HUD;

import Entities.Sprite;
import Media.EFont;
import Media.EImage;
import Media.Media;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;

import java.awt.*;

/**
 * Shows and renders Pacmans lives as part of the game's HUD system.
 */
public class LivesJPanel extends JPanel {
    
    private int lives = 0;
    private JLabel label;
    
    public LivesJPanel(int lives) {
        super();
        label = new JLabel("LIVES ");
        label.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int)Settings.get(EParam.label_size)));
        label.setForeground(((Color)Settings.get(EParam.label_color)));
        add(label);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(false);
        updateLives(lives);
    }
    
    /**
     * Sets the new lives to be displayed.
     * @param lives the new lives to be displayed
     */
    public void updateLives(int lives) {
    
        while(lives < this.lives && getComponentCount() > 1) {
            remove(getComponentCount()-1); this.lives--;
        }
        while(lives > this.lives) {
            add(new JLabel(new ImageIcon(Media.getImg(EImage.live)))); this.lives++;
        }
        repaint();
    }
}