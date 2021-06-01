package Painter.HUD;

import Painter.BlinkAnimator;
import Media.*;
import Settings.*;

import javax.swing.*;
import java.awt.*;


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

    public void updateRound(int round) {
        this.currentRound = round;
        setBounds(getX(),getY(), ("Round "+currentRound).length()*getFont().getSize(), getFont().getSize());
    }

    public BlinkAnimator getBlinkAnimator() { return blinkAnimator; }

}

