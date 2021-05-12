import Painter.Scaler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScalerTest {
    @Test
    public void setNewFramesizeTest() {
        int size = Scaler.getSize();
        
        Scaler.setNewsize(4000);
        assertEquals((double)4000/size, Scaler.getScale_factor(), 0.1);
    
        Scaler.setNewsize(69);
        assertEquals((double)69/size, Scaler.getScale_factor(), 0.1);
    }
    
    @Test
    public void scaleTest() {
        int size = Scaler.getSize();
        
        Scaler.setNewsize(2000);
        double scale = Scaler.getScale_factor();
        assertEquals(50*scale, Scaler.scale(50), 0.1);
    
        Scaler.setNewsize(420);
        scale = Scaler.getScale_factor();
        assertEquals(1212*scale, Scaler.scale(1212), 0.1);
    
        Scaler.setNewsize(69);
        scale = Scaler.getScale_factor();
        assertEquals(666*scale, Scaler.scale(666), 0.1);
    }
}
