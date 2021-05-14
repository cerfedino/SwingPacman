package Game;

import Entities.Ghost;
import Entities.Sprite;
import Map.*;
import Painter.Scaler;

import java.util.ArrayList;
import java.util.Random;

/**
 * Saves all the valuable stats and data about the game.
 */
public class GameState {
    //private Pacman pacman;
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private Map map;
    
    /**
     * Initializes the GameState object.
     */
    public GameState() {
        map = new Map();
        
        // Demo
        ArrayList<Edge> edges = map.getEdges();
        Random rand = new Random();
        for (int x=0;x<4;x++) {
            Ghost g = new Ghost(edges.get(rand.nextInt(edges.size())));
            ghosts.add(g);
            Game.painter().registerSprite(g);
        }
        ////////////////
        
    }
    
    
    
    //////////////
    // Getters and Setters below
    public Map getMap(){
        return map;
    }
}
