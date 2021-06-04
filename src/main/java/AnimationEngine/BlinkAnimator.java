package AnimationEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * When triggered, makes a JComponent blink.
 */
public class BlinkAnimator extends Timer {
    
    private JComponent component;
    private boolean finalState;
    
    /**
     * Initializes a BlinkAnimator object.
     * @param component the JComponent to blink
     * @param interval the interval at which to blink the JComponent
     * @param finalState whether the JComponent should be visible after the blinking has been stopped
     */
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
    
    /**
     * Stops the blinking animation.
     */
    @Override
    public void stop(){
        super.stop();
        component.setVisible(finalState);
    }
}
