package AnimationEngine;

import Entities.Ghost;
import Entities.Pacman;
import Entities.Sprite;
import Media.EImage;

import java.util.EnumMap;


/**
 * Handles all the animations of the game.
 */
public class AnimationManager {
    private static EnumMap<EImage,EImage> animation;
    
    /**
     * Creates all the initial animation mapping.
     */
    public static void initAnimations() {
        EnumMap<EImage,EImage> newAnimation =new EnumMap<>(EImage.class);
        
        ///////////
        
        newAnimation.put(EImage.pacman_left_1,EImage.pacman_left_2);
        newAnimation.put(EImage.pacman_left_2,EImage.pacman_left_3);
        newAnimation.put(EImage.pacman_left_3,EImage.pacman_left_4);
        newAnimation.put(EImage.pacman_left_4,EImage.pacman_left_1);
    
    
        newAnimation.put(EImage.pacman_right_1,EImage.pacman_right_2);
        newAnimation.put(EImage.pacman_right_2,EImage.pacman_right_3);
        newAnimation.put(EImage.pacman_right_3,EImage.pacman_right_4);
        newAnimation.put(EImage.pacman_right_4,EImage.pacman_right_1);
    
    
        newAnimation.put(EImage.pacman_down_1,EImage.pacman_down_2);
        newAnimation.put(EImage.pacman_down_2,EImage.pacman_down_3);
        newAnimation.put(EImage.pacman_down_3,EImage.pacman_down_4);
        newAnimation.put(EImage.pacman_down_4,EImage.pacman_down_1);
    
    
        newAnimation.put(EImage.pacman_up_1,EImage.pacman_up_2);
        newAnimation.put(EImage.pacman_up_2,EImage.pacman_up_3);
        newAnimation.put(EImage.pacman_up_3,EImage.pacman_up_4);
        newAnimation.put(EImage.pacman_up_4,EImage.pacman_up_1);
    
        ///////////
     
        
        newAnimation.put(EImage.ghost1_left,EImage.ghost1_left);
        newAnimation.put(EImage.ghost1_right,EImage.ghost1_right);
        newAnimation.put(EImage.ghost1_up,EImage.ghost1_up);
        newAnimation.put(EImage.ghost1_down,EImage.ghost1_down);
    
        newAnimation.put(EImage.ghost2_left,EImage.ghost2_left);
        newAnimation.put(EImage.ghost2_right,EImage.ghost2_right);
        newAnimation.put(EImage.ghost2_up,EImage.ghost2_up);
        newAnimation.put(EImage.ghost2_down,EImage.ghost2_down);
    
        newAnimation.put(EImage.ghost3_left,EImage.ghost3_left);
        newAnimation.put(EImage.ghost3_right,EImage.ghost3_right);
        newAnimation.put(EImage.ghost3_up,EImage.ghost3_up);
        newAnimation.put(EImage.ghost3_down,EImage.ghost3_down);
    
        newAnimation.put(EImage.ghost4_left,EImage.ghost4_left);
        newAnimation.put(EImage.ghost4_right,EImage.ghost4_right);
        newAnimation.put(EImage.ghost4_up,EImage.ghost4_up);
        newAnimation.put(EImage.ghost4_down,EImage.ghost4_down);
        
        
        animation = newAnimation;
    }
    
    /**
     * Returns the next frame of the specified ENUM image.
     * @param currFrame the current frame of the animation
     * @return the next frame image
     */
    public static EImage getNextFrame(EImage currFrame) {
        return animation.containsKey(currFrame) ? animation.get(currFrame) : currFrame;
    }
    
    /**
     * Gets the first frame of the animation for the specified Sprite.
     *  Method gets usually called when a Sprite changes significantly its state.
     *  (e.g a MovingEntity changes direction / Ghosts gets vulnerable ecc.)
     * @param s the Sprite to return the first frame for.
     * @return the first frame
     */
    public static EImage getFirstFrame(Sprite s) {
        
        if (s instanceof Pacman) {
            Pacman p = (Pacman)s;
    
            // Check ghost type
            if (p.getDirection() == null)
                return EImage.pacman_right_1;
            
            switch(((Pacman) s).getDirection()) {
                case UP:
                    return EImage.pacman_up_1;
                case DOWN:
                    return EImage.pacman_down_1;
                case LEFT:
                    return EImage.pacman_left_1;
                default: // RIGHT
                    return EImage.pacman_right_1;
            }
        } else if (s instanceof Ghost){
            Ghost g=(Ghost) s;
    
            // Check ghost type
            if(!g.isVulnerable()) {
                if (g.getType() == null)
                    return EImage.placeholder;
                switch (g.getType()){
                    case ghost1:
                        // Check Ghost Direction
                        if (g.getDirection() == null)
                            return EImage.ghost1_right;
                        switch (g.getDirection()){
                            case UP:
                                return EImage.ghost1_up;
                            case DOWN:
                                return EImage.ghost1_down;
                            case LEFT:
                                return EImage.ghost1_left;
                            default:
                                return EImage.ghost1_right;
                        }
                    case ghost2:
                        // Check Ghost Direction
                        if (g.getDirection() == null)
                            return EImage.ghost2_right;
                        switch (g.getDirection()){
                            case UP:
                                return EImage.ghost2_up;
                            case DOWN:
                                return EImage.ghost2_down;
                            case LEFT:
                                return EImage.ghost2_left;
                            default:
                                return EImage.ghost2_right;
                        }
                    case ghost3:
                        // Check Ghost Direction
                        if (g.getDirection() == null)
                            return EImage.ghost3_right;
                        switch (g.getDirection()){
                            case UP:
                                return EImage.ghost3_up;
                            case DOWN:
                                return EImage.ghost3_down;
                            case LEFT:
                                return EImage.ghost3_left;
                            default:
                                return EImage.ghost3_right;
                        }
                    case ghost4:
                        // Check Ghost Direction
                        if (g.getDirection() == null)
                            return EImage.ghost4_right;
                        switch (g.getDirection()){
                            case UP:
                                return EImage.ghost4_up;
                            case DOWN:
                                return EImage.ghost4_down;
                            case LEFT:
                                return EImage.ghost4_left;
                            default:
                                return EImage.ghost4_right;
                        }
                }
            } else { // IF THE GHOST IS VULNERABLE
                return s.getImage();
            }
        }
        // Add other animations here
        
        return s.getImage();
    }
}
