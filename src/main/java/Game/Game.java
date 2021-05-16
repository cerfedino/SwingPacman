package Game;

import Entities.Ghost;
import Entities.MovingEntity;
import Painter.*;

import java.util.ArrayList;

/**
 * The main class that starts the game.
 */
public class Game {
    
    private static GameState gamestate;
    private static Painter painter;
    private static GameThread gamethread;
    /**
     * The main method. Starts the game
     * @param args the arguments passed to the main method
     */
    public static void main(String[] args){
        
        painter = new Painter();
        
        gamestate = new GameState();
        painter.registerMap(gamestate.getMap());
        
        gamethread = new GameThread();
        gamethread.run();
        
    }
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() {
        return painter;
    }
    public static GameState gamestate() {
        return gamestate;
    }
    
    
}

/**
 * Responsible of the Sprites moving and interacting with each other.
 */
class GameThread implements Runnable {
    
    private boolean paused = false;
    
    @Override
    public void run(){
        try{
            while(true){
                while (!paused){
                    ArrayList<Ghost> entities = Game.gamestate().getGhosts();
                    for (Ghost g : entities){
                        g.step();
                    }
                    Thread.sleep(20);
                }
            }
        } catch (Exception ae){
            ae.printStackTrace();
        }
    }
    
    /**
     * Pauses the Thread.
     */
    public void pause() {
        paused = true;
    }
    
    /**
     * Unpauses the Thread.
     */
    public void unpause() {
        paused = false;
    }
}
