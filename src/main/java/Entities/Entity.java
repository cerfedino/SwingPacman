package Entities;

import Map.Edge;
import Media.EImage;


public class Entity extends Sprite {
    
    private Edge location;
    
    /**
     * Initializes an Entity object.
     *
     * @param x  the x coordinate of the Sprite
     * @param y  the y coordinate of the Sprite
     * @param en the EImage ENUM for the image
     */
    public Entity(int x, int y, EImage en){
        super(x, y, en);
    }
    
    /**
     * Initializes an Entity object.
     *
     * @param x  the x coordinate of the Sprite
     * @param y  the y coordinate of the Sprite
     * @param en the EImage ENUM for the image
     * @param location the Edge in which the Entity is located
     */
    public Entity(int x, int y, EImage en, Edge location){
        super(x, y, en);
        this.location = location;
    }
    
    
    ///////////////////
    // Setters and getters below
    
    public void setLocation(Edge location){
        this.location=location;
    }
}
