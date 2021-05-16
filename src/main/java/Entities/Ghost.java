package Entities;

import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;
import Settings.*;
import Game.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

/**
 * An enemy Ghost.
 */
public class Ghost extends MovingEntity{
    
    
    
    EGhostType type;
    /**
     * Initializes a Ghost object
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
    
    @Override
    public void removeSprite() {
        super.removeSprite();
        Game.gamestate().removeGhost(this);
    }
    
    @Override
    public void onCollision(Entity e) {
    
    }
    
    @Override
    public void makeTurn(Node n) {
        // IF there is priority queue, unqueue that one,
        // otherwise super.makeTurn()
        super.makeTurn(n);
    }
    
    @Override
    public void addTurn(EDirection direction) {
        // TODO: Implement if close to pacman, takes shortest route, otherwise a random one
        if (getCurrEdge().isAtExtremes(this)) {
            ArrayList<EDirection> possible_turns = getCurrEdge().getExtreme(this).getPossibleTurns();
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
            if (possible_turns.size()>1)
                possible_turns.remove(inverse);
            System.out.println(possible_turns.size());
            Random r = new Random();
            turnQueue.add(possible_turns.get(new Random().nextInt(possible_turns.size())));
            
        }
        
    }
    
}
