package Game;

import AudioEngine.FunctionCallback;
import Map.EDirection;
import Media.EAudio;
import Settings.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Handles the Keyboard input from the user
 */
public class GameInputManager implements KeyListener {
    
    private HashMap<Integer, EDirection> key_direction_mapping = new HashMap<>();
    
    /**
     * Initializes the GameInputManager object.
     */
    public GameInputManager() {
        key_direction_mapping.put((int)Settings.get(EParam.KEY_turn_down),EDirection.DOWN);
        key_direction_mapping.put((int)Settings.get(EParam.KEY_turn_up),EDirection.UP);
        key_direction_mapping.put((int)Settings.get(EParam.KEY_turn_left),EDirection.LEFT);
        key_direction_mapping.put((int)Settings.get(EParam.KEY_turn_right),EDirection.RIGHT);
    }
    
    public void keyTyped(KeyEvent e) {
    
    }
    
    /**
     * Handles the keyboard key presses to control the Pacman.
     * @param e the key event
     */
    public void keyPressed(KeyEvent e) {
        if (key_direction_mapping.containsKey(e.getKeyCode())) {
            System.out.println("**********\nGAME INPUT MANAGER\n" + "Key pressed: ["+e.getKeyCode()+":"+e.getKeyChar()+"]");
            Game.gamestate().getPacman().addTurn(key_direction_mapping.get(e.getKeyCode()));
            return;
        }
        
        // TODO: Remove placeholder for audio Demo
        if(e.getKeyChar() == 'a') {
            Game.audioengine().playIfNotAlready(EAudio.placeholder, new FunctionCallback() {
                @Override
                public void callback(){
                    System.out.println("Audio is done playing, here's the callback !!!");
                }
            });
        }
        if (e.getKeyChar() == 'p') {
            Game.gamethread().pause();
        }
        if (e.getKeyChar() == 'u') {
            Game.gamethread().unpause();
        }
        if (e.getKeyChar() == 'd') {
            Game.gamethread().performDeathSequence();
        }
    }
    
    public void keyReleased(KeyEvent e) {
    
    }
}
