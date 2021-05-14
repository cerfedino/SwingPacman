package Game;

import Entities.Ghost;
import Entities.Sprite;
import Map.*;
import Painter.Scaler;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    //private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    private Map map;
    
    public GameState() {
        map = new Map();
        
        // Demo
        ArrayList<Edge> edges = map.getEdges();
        Random rand = new Random();
        for (int x=0;x<4;x++) {
            Ghost g = new Ghost(edges.get(rand.nextInt(edges.size())));
            Game.painter().registerSprite(g);
        }
    }
    
    
    
    //////////////
    // Getters and Setters below
    public Map getMap(){
        return map;
    }
}
