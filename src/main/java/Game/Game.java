package Game;


import Painter.*;


/**
 * The main class that starts the game.
 */
public class Game {
    private static GameState gamestate;
    private static Painter painter;
    
    private static GameThread gamethread;
    private static GameInputManager gameinput;
    
    public Game() {
        gameinput = new GameInputManager();
        painter = new Painter(gameinput);
    
        gamestate = new GameState();
        painter.registerMap(gamestate.getMap());
    
        gamethread = new GameThread();
        gamethread.run();
    }
    
    public static void main(String[] args){
        new Game();
    }
    
    public static void gameOver() {
        // TODO: Display gameover image
        Game.gamethread().freezeEntities();
    }
    
    
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() { return painter; }
    public static GameState gamestate() { return gamestate; }
    public static GameThread gamethread() { return gamethread; };
}


