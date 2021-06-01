package Painter.HUD;

import Media.*;
import Settings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RoundJLabel extends JLabel {

    private int roundN = 1;
    private Timer timerLB;


    public RoundJLabel() {
        super();
        timerLB = new Timer(500, (ActionListener) new LbBlink(this));
        setOpaque(false);
        setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int)((int)Settings.get(EParam.label_size)*1.2)));
        updateRound(1);

        setForeground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        setText("round "+ roundN);
        super.paintComponent(g);
        remove(this);
        Toolkit.getDefaultToolkit().sync();
    }

    public void updateRound(int round) {
        this.roundN = round;
        setBounds(getX(),getY(), ("round "+roundN).length()*getFont().getSize(), getFont().getSize());
    }

    public Timer getTimerLB() { return timerLB; }

}
class LbBlink implements ActionListener {
    private javax.swing.JLabel label;
    private java.awt.Color cor1 = java.awt.Color.yellow;
    private java.awt.Color cor2 = java.awt.Color.black;
    private int count;
    
    public LbBlink(javax.swing.JLabel label){
        this.label = label;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(count % 2 == 0)
            label.setForeground(cor1);
        else
            label.setForeground(cor2);
        count++;
    }
}