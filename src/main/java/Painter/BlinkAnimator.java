package Painter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlinkAnimator extends Timer {
    
    private JComponent component;
    private boolean finalState;
    
    public BlinkAnimator(JComponent component, int interval, boolean finalState){
        super(interval, new ActionListener() {
            boolean count = true;
            
            @Override
            public void actionPerformed(ActionEvent e){
                component.setVisible(count);
                count =! count;
            }
        });
        this.component=component;
        this.finalState = finalState;
    }
    
    @Override
    public void stop(){
        super.stop();
        component.setVisible(finalState);
    }
}
