package Painter.HUD;

import Media.*;
import Settings.*;

import javax.swing.*;
import java.awt.*;

/**
 * The score JLabel that shows Pacmans score as part of the game's HUD system.
 */
public class ScoreJLabel extends JLabel {
    
    private long score;
    
    public ScoreJLabel() {
        super();
        setOpaque(false);
        setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int)Settings.get(EParam.label_size)));
        updateScore(0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        setForeground((Color)Settings.get(EParam.label_color));
        setText("SCORE     "+score);
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Sets the new score to be displayed.
     * @param score the new score to be displayed
     */
    public void updateScore(long score) {
        this.score = score;
        setBounds(getX(),getY(), ("SCORE: "+score).length()*(int)Settings.get(EParam.label_size), (int)Settings.get(EParam.label_size));
    }
}
