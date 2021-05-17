package Game;

import Entities.Ghost;
import Map.EDirection;
import Painter.*;
import Settings.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The main class that starts the game.
 */
public class Game {
    
    private static GameState gamestate;
    private static Painter painter;
    private static GameThread gamethread;
    private static GameInputManager gameinput;
    /**
     * The main method. Starts the game
     * @param args the arguments passed to the main method
     */
    public static void main(String[] args){
        gameinput = new GameInputManager();
        painter = new Painter(gameinput);
        
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
            GameState gamestate =  Game.gamestate();
            while(true){
                ArrayList<Ghost> entities = gamestate.getGhosts();
                for (Ghost g : entities){
                    g.step();
                }
                gamestate.getPacman().step();
                Thread.sleep(20);
            }
        } catch (Exception ae){
            ae.printStackTrace();
        }
    }
}

