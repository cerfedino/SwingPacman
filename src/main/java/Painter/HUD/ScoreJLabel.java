package Painter.HUD;

import Media.*;
import Settings.*;

import javax.swing.*;
import java.awt.*;

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
    
    public void updateScore(long score) {
        this.score = score;
        setBounds(getX(),getY(), ("SCORE: "+score).length()*(int)Settings.get(EParam.label_size), (int)Settings.get(EParam.label_size));
    }
}
