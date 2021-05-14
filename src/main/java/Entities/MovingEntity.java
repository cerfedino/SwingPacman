package Entities;

import Map.EDirection;
import Map.Edge;
import Map.Node;
import Media.EImage;

import java.util.ArrayList;
import java.util.Random;

public class MovingEntity extends Entity {
    
    private double speed;
    private EDirection direction;
    private boolean colliding = true;
    private ArrayList<EDirection> turnQueue;
    
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
    
    private void step() {
    
    }
    
    void addTurn(EDirection direction) {
    
    }
    
    ////////////////
    // Setters and Getters below
    
}
