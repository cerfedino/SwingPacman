package Entities;

import AnimationEngine.AnimationManager;
import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;

import java.util.LinkedList;

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
     * @param direction the initial direction of the MovingEntity
     * @param speed the speed of the MovingEntity
     */
    public MovingEntity(EImage en, Edge location, EDirection direction, int speed){
        super(location.getFrom().getX(), location.getFrom().getY(), en, location);
        
        this.direction = direction;
        this.speed = speed;
    
        getInitialAnimationFrame();
    }
    
    /**
     * Takes a step in the direction the MovingEntity is facing, along the Edge.
     */
    public void step() {
        // Move along the edge if hes not at the end,
        // otherwise, call makeTurn();
        EntityManager.checkCollisions(this);
        
        if (getCurrEdge().isAtExtremes(this)){
            makeTurn(getCurrEdge().getExtreme(this));
        }
        if (direction != null){
            switch (direction){
                case LEFT:
                case RIGHT:
                    setX(getCurrEdge().moveAlongEdge(direction, this));
                    break;
                case UP:
                case DOWN:
                    setY(getCurrEdge().moveAlongEdge(direction, this));
                    break;
            }
        }
    }
    
    /**
     * Unqueues a turn and tries to perform it,
     *  otherwise tries to generate a new turn by calling addTurn().
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
            setImage(AnimationManager.getFirstFrame(this));
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
    
    public void resetEntity() {
        setDirection(null);
        turnQueue = new LinkedList<>();
    }
    
    public void setDirection(EDirection direction){
        this.direction=direction;
        setImage(AnimationManager.getFirstFrame(this));
    }
    
    public EDirection getDirection(){
        return direction;
    }
    
    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
