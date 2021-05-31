package Painter;

import Settings.*;

import javax.swing.*;
import java.awt.*;

public class ScoreJLabel extends JLabel {
    
    private long score;
    
    public ScoreJLabel() {
        super();
        setOpaque(false);
        setFont(new Font("Sans Serif", Font.BOLD, (int)Settings.get(EParam.score_label_size)));
        updateScore(0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        setForeground((Color)Settings.get(EParam.score_label_color));
        setText("SCORE: "+score);
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void updateScore(long score) {
        this.score = score;
        setBounds(getX(),getY(), ("SCORE: "+score).length()*(int)Settings.get(EParam.score_label_size), (int)Settings.get(EParam.score_label_size));
    }
}
