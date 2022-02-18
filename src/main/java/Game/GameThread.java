package Game;

import AudioEngine.AudioEngine;
import AudioEngine.FunctionCallback;
import AudioEngine.PlaybackMode;
import Entities.Ghost;
import Media.EAudio;
import Painter.Painter;

import java.util.ArrayList;

/**
 * Responsible of the Sprites moving and interacting with each other.
 */
public class GameThread implements Runnable {
    
    private static boolean paused = false;
    private static boolean stepping = true;
    
    @Override
    public void run() {
        try{
            performRoundIntro();
            
            while (true){
                // DONT REMOVE THIS. Apparently if you dont print here, everything breaks.
                // Java makes no sense
                // TODO: Investigate this paranormal behaviour
                System.out.print("");
                
                while (!paused && stepping){
                    stepEntities();
                    Thread.sleep(20);
                }
                
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }
    
    /**
     * Handles what happens on game over.
     */
    public void gameOver() {
        freezeEntities();
    }
    
    /**
     * Handles what happens on a new round sequence.
     */
    public void performRoundIntro() {
        Painter.getRoundHUD().getBlinkAnimator().start();
        freezeEntities();
        AudioEngine.play(EAudio.round_start, PlaybackMode.regular, new FunctionCallback() {
            @Override
            public void callback() {
                System.out.println("Finished round start");
                Painter.getRoundHUD().getBlinkAnimator().stop();
                unfreezeEntities();
            }
        });
    }
    
    /**
     * handles what happens on a death sequence
     */
    public void performDeathSequence(){
        freezeEntities();
        
        AudioEngine.play(EAudio.death_sound, PlaybackMode.regular, new FunctionCallback() {
            @Override
            public void callback() {
                System.out.println("Finished death sound");
                Game.gamestate().reshuffleEntityPositions();
                performRoundIntro();
            }
        });
    }
    
    /**
     * Gathers all the MovingEntities and makes them step.
     */
    protected void stepEntities(){
        ArrayList<Ghost> entities = Game.gamestate().getGhosts();
        for (Ghost g : entities){
            g.step();
        }
        Game.gamestate().getPacman().step();
    }
    
    /**
     * Pauses the game thread
     */
    public void pause(){
        paused = true;
        AudioEngine.pauseAll();
    }
    
    /**
     * Unpauses the game thread
     */
    public void unpause(){
        paused = false;
        AudioEngine.resumeALl();
    }
    
    public void freezeEntities() {
        stepping = false;
    }
    public void unfreezeEntities() {
        stepping = true;
    }
}
