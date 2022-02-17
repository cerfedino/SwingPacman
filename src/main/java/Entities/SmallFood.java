package Entities;

import Map.Edge;
import Media.EImage;
import Settings.EParam;
import Settings.Settings;

/**
 * Simple food that merely increases Pacman's score upon geting eaten.
 */
public class SmallFood extends Food{
    
    public SmallFood(int x, int y, Edge currEdge){
        super(x, y, EImage.small_food, currEdge, (int)Settings.get(EParam.small_food_score));
    }
    
    @Override
    public void onCollision(Entity e){
        // Just disappears
        super.onCollision(e);
    }
}
