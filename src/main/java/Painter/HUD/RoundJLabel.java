package Painter.HUD;

import AnimationEngine.BlinkAnimator;
import Media.EFont;
import Media.Media;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * The blinking JLabel that shows the Round number as part of the game's HUD system.
 */
public class RoundJLabel extends JLabel {

    private int currentRound = 1;
    private BlinkAnimator blinkAnimator;


    public RoundJLabel() {
        super();
        blinkAnimator= new BlinkAnimator(this,500, false);
        setOpaque(false);
        setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int)((int)Settings.get(EParam.label_size)*1.2)));
        setForeground(Color.yellow);
        updateRound(1);
    }

    @Override
    public void paintComponent(Graphics g) {
        setText("Round "+currentRound);
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Sets the new round to be displayed.
     * @param round the new round to be displayed
     */
    public void updateRound(int round) {
        this.currentRound = round;
        setBounds(getX(),getY(), ("Round "+currentRound).length()*getFont().getSize(), getFont().getSize());
    }

    public BlinkAnimator getBlinkAnimator() { return blinkAnimator; }

}

