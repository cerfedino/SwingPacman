package Game;

import Entities.EGhostType;
import Entities.Ghost;
import Entities.MovingEntity;
import Map.*;

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
        
        // Initializes the Map.
        map = new Map();
        
        ///////////////
        //// Demo
        ArrayList<Edge> edges = map.getEdges();
        Random rand = new Random();
        Ghost g1 = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost1);
        Ghost g2 = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost2);
        Ghost g3 = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost3);
        Ghost g4 = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost4);
        ghosts.add(g1);
        ghosts.add(g2);
        ghosts.add(g3);
        ghosts.add(g4);
        Game.painter().registerSprite(g1);
        Game.painter().registerSprite(g2);
        Game.painter().registerSprite(g3);
        Game.painter().registerSprite(g4);
        ////////////////
        
        
    }
    
    
    //////////////
    // Getters and Setters below
    public Map getMap(){
        return map;
    }
    
    public void removeGhost(Ghost g) {
        if (ghosts.contains(g)) ghosts.remove(g);
    }
    
    public ArrayList<Ghost> getGhosts(){
        return ghosts;
    }
}
