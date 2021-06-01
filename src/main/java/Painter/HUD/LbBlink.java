package Painter.HUD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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