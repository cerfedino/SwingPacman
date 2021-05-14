
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
    
        assertEquals(100,s.getSpriteX());
        assertEquals(200,s.getSpriteY());
    
        s.setX(420);
        assertEquals(420,s.getSpriteX());
        assertEquals(200,s.getSpriteY());
    
        s.setY(69);
        assertEquals(420,s.getSpriteX());
        assertEquals(69,s.getSpriteY());
        
        s.setImage(EImage.ghost1);
        assertEquals(420,s.getSpriteX());
        assertEquals(69,s.getSpriteY());
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
