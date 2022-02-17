package Entities;

import Map.Edge;
import Media.EImage;

/**
 * A Food entity that can be eaten by the Pacman.
 */
public class Food extends Entity{
    private int points;
    
    /**
     * Initializes a Food object.
     * @param x the X coordinate of the Food
     * @param y the Y coordinate of the Food
     * @param en the image for the Food Sprite
     * @param currEdge the current edge location of the Food
     * @param points the Food's worth in points
     */
    public Food(int x, int y, EImage en, Edge currEdge, int points){
        super(x, y, en, currEdge);
        this.points = points;
    }
    
    /**
     * Handles the consequences when this object collides with another Entity
     * @param e the Entity collided with
     */
    @Override
    public void onCollision(Entity e){
        if(e instanceof  Pacman){
            removeSprite();
            getCurrEdge().getFood().remove(this);
        }
    }
    
    ///////////////
    // Getters and setters below
    public int getPoints() {
        return points;
    }
}
