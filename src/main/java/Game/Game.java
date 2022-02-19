package Game;


import Media.Media;
import Painter.Painter;

/**
 * The main class that starts the game.
 */
public class Game {
    private static GameState gamestate;
    private static Painter painter;
    
    private static GameThread gamethread;
    private static GameInputManager gameinput;
    
    /**
     * Starts the Game.
     */
    public Game() {
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("prism.allowhidpi", "false");
        System.setProperty("sun.java2d.uiScale", "1");
        
        gameinput = new GameInputManager();
        painter = new Painter(gameinput);
    
        gamestate = new GameState();
        painter.registerMap(gamestate.getMap());
    
        gamethread = new GameThread();
        Thread t = new Thread(gamethread);
        t.start();
    }
    
    public static void main(String[] args){
        new Game();
    }
    
    /**
     * Handles the gameover phase.
     */
    public static void gameOver() {
        // TODO: Make gameover panel showing score and misc stats about the game.
        Game.gamethread().freezeEntities();
    }
    
    
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() { return painter; }
    public static GameState gamestate() { return gamestate; }
    public static GameThread gamethread() { return gamethread; };
}


