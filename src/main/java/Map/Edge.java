package Map;

import Entities.*;
import Settings.EParam;
import Settings.Settings;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a walkable path connecting two nodes together
 */
public class Edge {
    private final Node from;
    private final Node to;
    
    private double length;
    
    private EOrientation orientation;
    
    private ConcurrentLinkedQueue<Food> food =  new ConcurrentLinkedQueue<>();
    
    /**
     * Initializes an Edge object.
     * @param from the starting Node
     * @param to the end Node
     */
    public Edge(Node from, Node to) {
    
        boolean switch_nodes =  false;
        if (from.getX() == to.getX()) {
            orientation = EOrientation.VERTICAL;
            switch_nodes = from.getY() > to.getY();
        } else if(from.getY() == to.getY()){
            orientation = EOrientation.HORIZONTAL;
            switch_nodes = from.getX() > to.getX();
        }
        
        this.from = switch_nodes? to   : from;
        this.to =   switch_nodes? from : to;
        
        length = calcLength();
        
        spawnFood();
    }
    
    /**
     * Calculates the length of the Edge.
     * @return the length of the Edge
     */
    private double calcLength() {
        int x1 = from.getX();
        int y1 = from.getY();
    
        int x2 = to.getX();
        int y2 = to.getY();
    
        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }
    
    /**
     * Spawns food along the Edge.
     */
    protected void spawnFood() {
        calcLength();
        food = new ConcurrentLinkedQueue<>();
        
        int minX = Math.min(from.getX(), to.getX());
        int minY = Math.min(from.getY(), to.getY());
    
        int path_width = (int)Settings.get(EParam.path_width);
        int offset = (int)(((length - path_width*2) % (int)Settings.get(EParam.food_distancing) ) / 2)+ path_width;
        int food_distancing = (int)Settings.get(EParam.food_distancing);
    
        Random r = new Random();
        for(int i = offset; i <= length-offset; i+=food_distancing) {
            Food f;
            if (r.nextInt(100) < (int)Settings.get(EParam.special_food_spawn_odd)){
                f = new LargeFood(minX,minY,this);
            } else {
                f = new SmallFood(minX,minY,this);
            }
            switch(orientation) {
                case VERTICAL:
                    f.setY(minY+i);
                    break;
                case HORIZONTAL:
                    f.setX(minX+i);
                    break;
            }
            food.add(f);
        }
        for (Food f: food) {
            Game.Game.painter().registerSprite(f);
        }
    }
    
    /**
     * Returns an integer representing a coordinate moved in a direction along the Edge
     *  by a certain step size.
     * @param direction the direction to move the coordinate along
     * @return the coordinate moved along the Edge
     */
    public int moveAlongEdge(EDirection direction, MovingEntity s) {
        int stepsize = s.getSpeed();
        int pos;
        switch (direction) {
            case LEFT:
                stepsize *= -1;
            case RIGHT:
                pos = s.getSpriteX();
                int fromX = from.getX();
                int toX = to.getX();
                
                pos += stepsize;
                if (pos < Math.min(fromX, toX)) {
                    return Math.min(fromX, toX);
                } else if(pos > Math.max(fromX, toX)) {
                    return Math.max(fromX, toX);
                } else {
                    return pos;
                }
                
            case UP:
                stepsize *= -1;
            case DOWN:
                pos = s.getSpriteY();
                
                int fromY = from.getY();
                int toY = to.getY();
    
                pos += stepsize;
                if (pos < Math.min(fromY, toY)) {
                    return Math.min(fromY, toY);
                } else if(pos > Math.max(fromY, toY)) {
                    return Math.max(fromY, toY);
                } else {
                    return pos;
                }
        }
        
        return 0;
    }
    
    /**
     * Returns true whether a Sprite is at the extremes of the Edge.
     * @param s the Sprite to check
     * @return whether a Sprite is at the extremes of the Edge
     */
    public boolean isAtExtremes(Sprite s) {
        int x = s.getSpriteX();
        int y = s.getSpriteY();
    
        return (from.getX() == x && from.getY() == y) || (to.getX() == x && to.getY() == y);
    }
    
    /**
     * Returns the extreme Node of the Edge at which the Sprite is located.
     *  To be used with isAtExtremes(Sprite s)
     * @param s the Sprite to check
     * @return the extreme Node of the Edge at which the Sprite is located.
     */
    public Node getExtreme(Sprite s) {
        int x = s.getSpriteX();
        int y = s.getSpriteY();
        
        if (from.getX() == x && from.getY() == y) return from;
        if (to.getX() == x && to.getY() == y) return to;
        
        return null;
    }
    
    /**
     * Gets the middle X coordinate of the Edge.
     * @return the middle X coordinate of the Edge.
     */
    public int getMiddlePointX() {
        int x=0;
        switch(orientation) {
            case HORIZONTAL:
                int minX = Math.min(from.getX(),to.getX());
                int maxX = Math.max(from.getX(),to.getX());
                x = minX + (maxX - minX)/2;
                break;
            case VERTICAL:
                x = from.getX();;
                break;
        }
        return x;
    }
    
    /**
     * Gets the middle Y coordinate of the Edge.
     * @return the middle Y coordinate of the Edge.
     */
    public int getMiddlePointY() {
        int y = 0;
        switch(orientation) {
            case HORIZONTAL:
                y = from.getY();
                break;
            case VERTICAL:
                int minY = Math.min(from.getY(),to.getY());
                int maxY = Math.max(from.getY(),to.getY());
                y = minY + (maxY - minY)/2;
                break;
        }
        return y;
    }
    
    //////////////
    // Getters and Setters below
    
    
    public ConcurrentLinkedQueue<Food> getFood(){
        return food;
    }
    
    public Node getFrom(){
        return from;
    }
    
    public Node getTo(){
        return to;
    }
    
    public double getLength(){
        return length;
    }
    
    public void setOrientation(EOrientation orientation) {
        this.orientation = orientation;
    }
    
    public EOrientation getOrientation(){
        return orientation;
    }
    
    public void setLength(double length){
        this.length=length;
    }
}
