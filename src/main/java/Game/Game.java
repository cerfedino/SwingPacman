package Game;

import AudioEngine.*;

import Painter.*;


/**
 * The main class that starts the game.
 */
public class Game {
    private static GameState gamestate;
    private static Painter painter;
    private static AudioEngine audioengine;
    
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
        audioengine = new AudioEngine();
        
        gamethread = new GameThread();
        gamethread.run();
    }
    
    
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() { return painter; }
    public static GameState gamestate() { return gamestate; }
    public static AudioEngine audioengine() { return audioengine; };
    public static GameThread gamethread() { return gamethread; };
}


