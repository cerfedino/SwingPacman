package Game;

import Entities.Ghost;
import Entities.Sprite;
import Map.Map;
import Media.EImage;
import Painter.*;

import static java.lang.Thread.sleep;

/**
 * The main class that starts the game/
 */
public class Game {
    
    private static GameState gamestate;
    private static Painter painter;
    
    /**
     * The main method. Starts the game
     * @param args the arguments passed to the main method
     */
    public static void main(String[] args){
        
        painter = new Painter();
        
        gamestate = new GameState();
        painter.registerMap(gamestate.getMap());
        
    }
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() {
        return painter;
    }
    
    
}
