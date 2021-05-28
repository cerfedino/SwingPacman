package Game;

import AudioEngine.*;
import Entities.Ghost;
import Map.EDirection;
import Media.EAudio;
import Painter.*;
import Settings.*;

import java.util.ArrayList;

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
    public static GameState gamestate() {
        return gamestate;
    }
    public static AudioEngine audioengine() { return audioengine; };
    
}

/**
 * Responsible of the Sprites moving and interacting with each other.
 */
class GameThread implements Runnable {
    
    private static boolean paused = false;
    
    @Override
    public void run(){
        try{
            performRoundIntro();
            while(true) {
                // DONT REMOVE THIS. Apparently if you dont print here, everything breaks.
                // Java makes no sense
                // TODO: Investigate this paranormal behaviour
                System.out.print("");
                
                if(!paused) {
                    stepEntities();
                    Thread.sleep(20);
                }
            }
        } catch (Exception ae){
            ae.printStackTrace();
        }
    }
    
    public void performRoundIntro() {
        pause();
        Game.audioengine().playOnce(EAudio.round_start, new FunctionCallback() {
            @Override
            public void callback(){
                System.out.println("Finished round start");
                unpause();
            }
        });
    }
    
    /**
     * Gathers all the MovingEntities and makes them step.
     */
    protected void stepEntities() {
        ArrayList<Ghost> entities = Game.gamestate().getGhosts();
        for (Ghost g : entities){
            g.step();
        }
        Game.gamestate().getPacman().step();
    }
    
    /**
     * Pauses the game thread
     */
    public void pause() {
        paused = true;
    }
    
    /**
     * Unpauses the game thread
     */
    public void unpause() {
        paused = false;
    }
}


