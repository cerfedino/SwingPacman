package Game;

import Entities.EGhostType;
import Entities.Ghost;
import Entities.Pacman;
import Map.*;
import Settings.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Saves all the valuable stats and data about the game.
 */
public class GameState {
    private Pacman pacman;
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private Map map;
    
    /**
     * Initializes the GameState object.
     */
    public GameState() {
        
        // Initializes the Map.
        map = new Map();
        
        
        // Creates the Ghosts
        ArrayList<Edge> edges = map.getEdges();
        Random rand = new Random();
        
        for(int i = 1; i <= (int)Settings.get(EParam.ghost_count); i++) {
            Ghost g;
            switch(i%4) {
                case 0:
                    g = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost4);
                    break;
                case 1:
                    g = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost1);
                    break;
                case 2:
                    g = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost2);
                    break;
                default: // 3
                    g = new Ghost(edges.get(rand.nextInt(edges.size())), EGhostType.ghost3);
                    break;
            }
            ghosts.add(g);
            
            Game.painter().registerSprite(g);
        }
        pacman = new Pacman(edges.get(rand.nextInt(edges.size())));
        Game.painter().registerSprite(pacman);
    }
    
    /**
     * Takes all the MovingEntities and moves them randomly to a new Edge.
     */
    //TODO: Move method to EntityManager
    public void reshuffleEntityPositions() {
        System.out.println("Reshuffling");
        Random rand = new Random();
        ArrayList<Edge> edges = map.getEdges();
    
        Edge e = (edges.get(rand.nextInt(edges.size())));
        pacman.setCurrEdge(e);
        pacman.setX(e.getFrom().getX());
        pacman.setY(e.getFrom().getY());
        pacman.resetEntity();
        for (Ghost g : ghosts) {
            e = (edges.get(rand.nextInt(edges.size())));
            g.setCurrEdge(e);
            g.setX(e.getFrom().getX());
            g.setY(e.getFrom().getY());
            g.resetEntity();
        }
    }
    
    //////////////
    // Getters and Setters below
    public Map getMap(){
        return map;
    }
    
    public void removeGhost(Ghost g) {
        if (ghosts.contains(g)) ghosts.remove(g);
    }
    public void removePacman(Pacman g) {
        pacman = null;
    }
    
    public ArrayList<Ghost> getGhosts(){
        return ghosts;
    }
    
    public Pacman getPacman(){
        return pacman;
    }
}
