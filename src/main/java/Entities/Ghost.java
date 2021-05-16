package Entities;

import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;
import Settings.*;
import Game.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * An enemy Ghost.
 */
public class Ghost extends MovingEntity{
    
    EGhostType type;
    private static boolean vulnerable = false;
    
    private LinkedList<EDirection> priorityQueue = new LinkedList<>();
    
    /**
     * Initializes a Ghost object.
     * @param location the Edge where the ghost is located.
     */
    public Ghost(Edge location, EGhostType ghost){
        super(EImage.ghost1, location, (int)Settings.get(EParam.ghost_speed));
        type = ghost;
        // Chooses the respective image for the ghost
        EImage img;
        switch(ghost) {
            case ghost1:
                img = EImage.ghost1;
                break;
            case ghost2:
                img = EImage.ghost2;
                break;
            case ghost3:
                img = EImage.ghost3;
                break;
            default:
                img = EImage.ghost4;
                break;
        }
        setImage(img);
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
        }else if (n.canTurn(priorityQueue.getFirst())){
            setDirection(priorityQueue.getFirst());
            setCurrEdge(n.getTurn(priorityQueue.removeFirst()));
        }else{
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
            EDirection inverse;
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
            
            // Removes the inverse of the direction from the possible turns,
            //  to eliminate the strange behaviour of going back at a turn.
            if (possible_turns.size()>1)
                possible_turns.remove(inverse);
            
            // Chooses a random turn to add to the queue
            Random r = new Random();
            turnQueue.add(possible_turns.get(new Random().nextInt(possible_turns.size())));
            
        }
        
    }
    
}
