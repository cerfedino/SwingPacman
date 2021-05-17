package Entities;

import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;

import java.util.*;

/**
 * An entity that has the ability of navigating and moving through the Map.
 */
public class MovingEntity extends Entity {
    
    private int speed;
    private EDirection direction;
    protected LinkedList<EDirection> turnQueue = new LinkedList<>();
    
    
    /**
     * Initializes a MovingEntity object.
     * @param en the image of the MovingEntity
     * @param location the Edge where the entity is located
     * @param speed the speed of the MovingEntity
     */
    public MovingEntity(EImage en, Edge location, int speed){
        super(location.getMiddlePointX(), location.getMiddlePointY(), en, location);
        this.speed = speed;
        
        // Chooses a random initial direction for the MovingSprite
        switch(location.getOrientation()) {
            case VERTICAL:
                switch(new Random().nextInt(1)) {
                    case 0:
                        direction = EDirection.UP;
                        break;
                    case 2:
                        direction = EDirection.DOWN;
                        break;
                }
                break;
            case HORIZONTAL:
                switch(new Random().nextInt(1)) {
                    case 0:
                        direction = EDirection.LEFT;
                        break;
                    case 1:
                        direction = EDirection.RIGHT;
                        break;
                }
                break;
        }
        
        
    }
    
    /**
     * Takes a step in the direction the MovingEntity is facing, along the Edge.
     */
    public void step() {
        // Move along the edge if hes not at the end,
        // otherwise, call makeTurn();
        
        switch(direction) {
            case LEFT:
            case RIGHT:
                setX(getCurrEdge().moveAlongEdge(direction,this));
                break;
            case UP:
            case DOWN:
                setY(getCurrEdge().moveAlongEdge(direction,this));
        }
        if (getCurrEdge().isAtExtremes(this)){
            makeTurn(getCurrEdge().getExtreme(this));
        }
    }
    
    /**
     * Unqueues a turn and tries to perform it,
     *  otherwise calls addTurn(null).
     * @param n the node to perform the turn on
     */
    public void makeTurn(Node n) {
        // TO OVERRIDE
        // if there is a turn in the queue, unqueue it.
        // otherwise addTurn();
        if (turnQueue.isEmpty()) {
            addTurn(null);
        }else if (n.canTurn(turnQueue.getFirst())){
            setDirection(turnQueue.getFirst());
            setCurrEdge(n.getTurn(turnQueue.removeFirst()));
        }else{
            turnQueue.removeFirst();
        }
    }
    
    /**
     * Handles the decision-making when it comes to choosing which turn to perform next.
     * @param direction used by Pacman to manually add a turn
     */
    public void addTurn(EDirection direction) {
        // TO OVERRIDE
    }
    
    ////////////////
    // Setters and Getters below
    
    public void setDirection(EDirection direction){
        this.direction=direction;
    }
    
    public EDirection getDirection(){
        return direction;
    }
    
    public int getSpeed(){
        return speed;
    }
}
