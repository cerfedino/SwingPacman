package Entities;

import Map.Edge;
import Media.EImage;

public class Food extends Entity{
    private int points;
    
    public Food(int x, int y, EImage en, Edge currEdge, int points){
        super(x, y, en, currEdge);
        this.points = points;
    }
    
    @Override
    public void onCollision(Entity e){
        if(e instanceof  Pacman){
            removeSprite();
    
            // TODO: TEMPORARY FIX. This throws ConcurrentMod exception.
            //  while figuring out a fix, we just set it to not collide anymore.
            //getCurrEdge().getFood().remove(this);
            setColliding(false);
            // TODO: Makes Ghosts vulnerable
        }
    }
    
    ///////////////
    // Getters and setters below
    public int getPoints() {
        return points;
    }
}
