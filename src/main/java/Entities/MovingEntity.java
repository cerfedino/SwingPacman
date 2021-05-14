package Entities;

import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;

import java.util.ArrayList;
import java.util.Random;

/**
 * An entity that has the ability of navigating and moving through the Map.
 */
public class MovingEntity extends Entity {
    
    private double speed;
    private EDirection direction;
    private ArrayList<EDirection> turnQueue = new ArrayList<>();
    
    
    /**
     * Initializes a MovingEntity object.
     * @param en the image of the MovingEntity
     * @param location the Edge where the entity is located
     * @param speed the speed of the MovingEntity
     */
    public MovingEntity(EImage en, Edge location, double speed){
        super(location.getMiddlePointX(), location.getMiddlePointY(), en, location);
        this.speed = speed;
        
        // Chooses a random initial direction for the MovingSprite
        switch(location.getOrientation()) {
            case VERTICAL:
                switch(new Random().nextInt(2)) {
                    case 1:
                        direction = EDirection.UP;
                        break;
                    case 2:
                        direction = EDirection.DOWN;
                        break;
                }
                break;
            case HORIZONTAL:
                switch(new Random().nextInt(2)) {
                    case 1:
                        direction = EDirection.LEFT;
                        break;
                    case 2:
                        direction = EDirection.RIGHT;
                        break;
                }
                break;
        }
        
        
    }
    
    /**
     * Takes a step in the direction the MovingEntity is facing, along the Edge.
     */
    private void step() {
    
    }
    
    /**
     * Adds a turn to the queue, to be unqueued once the entity reaches the end of an edge.
     * @param direction
     */
    void addTurn(EDirection direction) {
    
    }
    
    ////////////////
    // Setters and Getters below
    
}
