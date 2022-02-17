package Entities;

import AnimationEngine.BlinkAnimator;
import Game.Game;
import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * An enemy Ghost.
 */
public class Ghost extends MovingEntity {
    
    EGhostType type;
    private static boolean vulnerable = false;
    
    private LinkedList<EDirection> priorityQueue = new LinkedList<>();
    
    private boolean dead = false;
    
    /**
     * Initializes a Ghost object.
     * @param location the Edge where the ghost is located.
     */
    public Ghost(Edge location, EGhostType ghost) {
        super(null, location, EDirection.DOWN, (int)Settings.get(EParam.ghost_speed));
        type = ghost;
        
        // Chooses the respective image for the ghost
        resetGhostImg();
        
        getInitialAnimationFrame();
    }
    
    /**
     * Handles the complete removal of the Ghost from the game.
     */
    @Override
    public void removeSprite() {
        super.removeSprite();
        Game.gamestate().removeGhost(this);
    }
    
    /**
     * To be called when the Ghost collides with another Entity.
     * @param e the Entity the Ghost collided with
     */
    @Override
    public void onCollision(Entity e) {
    
    }
    
    @Override
    public void step() {
        if (!dead)
            super.step();
    }
    
    /**
     * Unqueues a turn from the queue and tries to perform it on the Node.
     * @param n The Node to perform the turn on.
     */
    @Override
    public void makeTurn(Node n) {
        // If there is priority queue unqueue that one,
        // otherwise super.makeTurn()
        if (priorityQueue.isEmpty()) {
            super.makeTurn(n);
        }else if (n.canTurn(priorityQueue.getFirst())) {
            setDirection(priorityQueue.getFirst());
            setCurrEdge(n.getTurn(priorityQueue.removeFirst()));
        }else {
            priorityQueue.removeFirst();
        }
        
    }
    
    /**
     * Handles the decision-making when it comes to choosing which turn to perform next.
     * @param direction used by Pacman to manually add a turn
     */
    @Override
    public void addTurn(EDirection direction) {
        // TODO: Implement if close to pacman
        //          1. If not vulnerable
        //                  tries to get closer to Pacman
        //          2. If vulnerable
        //                  tries to get away from Pacman
        //      otherwise a random one
        
        if (getCurrEdge().isAtExtremes(this)) {
            // The possible turns to be performed on the Node
            ArrayList<EDirection> possible_turns = getCurrEdge().getExtreme(this).getPossibleTurns();
            
            // The inverse of the current Ghost direction
            EDirection inverse = null;
            if(getDirection()!=null) {
                switch(getDirection()) {
                    case UP:
                        inverse = EDirection.DOWN;
                        break;
                    case DOWN:
                        inverse = EDirection.UP;
                        break;
                    case LEFT:
                        inverse = EDirection.RIGHT;
                        break;
                    default: // RIGHT
                        inverse = EDirection.LEFT;
                        break;
                }
            }
            
            // Removes the inverse of the direction from the possible turns,
            //  to eliminate the strange behaviour of going back at a turn.
            if (possible_turns.size()>1)
                possible_turns.remove(inverse);
            
            // Chooses a random turn to add to the queue
            Random r = new Random();
            turnQueue.add(possible_turns.get(new Random().nextInt(possible_turns.size())));
        }
    }

    public void setVulnerable(boolean to) {
        vulnerable = to;
        if (vulnerable) {
            setImage(EImage.ghost_vuln);
            setSpeed(((int)Settings.get(EParam.ghost_vuln_speed)));
        } else {
            resetGhostImg();
            setSpeed((int)Settings.get(EParam.ghost_speed));
        }
    }

    /**
     * Sets the Ghost image accordingly to the type of the ghost
     */
    public void resetGhostImg() {
        EImage img;
        switch(type) {
            case ghost1:
                img = EImage.ghost1_left;
                break;
            case ghost2:
                img = EImage.ghost2_right;
                break;
            case ghost3:
                img = EImage.ghost3_right;
                break;
            default:
                img = EImage.ghost4_right;
                break;
        }
        setImage(img);
    }
    
    ////////////////
    // Setters and getters below
    
    public void die() {
        dead = true;
        setColliding(false);
        
        BlinkAnimator b = new BlinkAnimator(this, 100, true);
        b.start();
        Timer t = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dead = false;
                b.stop();
                setColliding(true);
            }
        });
        t.start();
    }
    
    public EGhostType getType(){
        return type;
    }
    
    @Override
    public void resetEntity() {
        super.resetEntity();
        priorityQueue = new LinkedList<>();
    }
    
    public static boolean isVulnerable(){
        return vulnerable;
    }


}
