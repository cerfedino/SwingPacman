package Game;

import Map.Map;

public class GameState {
    //private Pacman pacman;
    //private ArrayList<Ghost> ghosts;
    private Map map;
    
    public GameState() {
        map = new Map();
    }
    
    
    
    //////////////
    // Getters and Setters below
    public Map getMap(){
        return map;
    }
}
