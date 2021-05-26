package Entities;

import Map.Edge;
import Media.EImage;

public class Food extends Entity{
    int points;
    
    public Food(int x, int y, EImage en, Edge currEdge, int points){
        super(x, y, en, currEdge);
        this.points = points;
    }
    
    @Override
    public void onCollision(Entity e){
        removeSprite();
    }
    
    ///////////////
    // Getters and setters below
    public int getPoints() {
        return points;
    }
}
