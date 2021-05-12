
import Entities.Sprite;
import Media.EImage;
import Painter.Painter;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpriteTest {
    
    @Test
    public void setPosTest() {
        Sprite s = new Sprite(0,0, EImage.pacman);
        
        s.setPos(100,200);
    
        assertEquals(100,s.getX());
        assertEquals(200,s.getY());
    
        s.setX(420);
        assertEquals(420,s.getX());
        assertEquals(200,s.getY());
    
        s.setY(69);
        assertEquals(420,s.getX());
        assertEquals(69,s.getY());
        
        s.setImage(EImage.ghost1);
        assertEquals(420,s.getX());
        assertEquals(69,s.getY());
    }
    
    @Test
    public void deleteSpriteTest() {
        Sprite s = new Sprite(0,0, EImage.pacman);
        Painter p = new Painter();
        
        p.registerSprite(s);
        assertTrue(p.isSpriteRegistered(s));
        
        p.unregisterSprite(s);
        assertFalse(p.isSpriteRegistered(s));
    }
}
