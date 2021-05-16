package Map;

import java.util.ArrayList;

/**
 * A Node in the Map. It sits in between Edges and offers the ability to perform turns towards multiple Edges.
 */
public class Node {
    private final int x;
    private final int y;
    
    private Edge left;
    private Edge right;
    private Edge up;
    private Edge down;
    
    
    /**
     * Initializes a Node object.
     * @param x the x coordinate of the Node
     * @param y the y coordinate of the Node
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns true whether a turn in the specified direction can be performed.
     * @param d the direction to check if its possible
     * @return whether the turn is possible
     */
    public boolean canTurn (EDirection d) {
        switch (d) {
            case UP:
                return up != null;
            case DOWN:
                return down != null;
            case LEFT:
                return left != null;
            case RIGHT:
                return right != null;
        }
        return false;
    }
    
    
    
    /////////////////////
    // Getters and Setters
    
    /**
     * Gets the outgoing Edge in the specified the direction.
     * @param d the direction of the outgoing Edge
     * @return the edge in the specified direction
     */
    public Edge getTurn(EDirection d) {
        switch (d) {
            case UP:
                return up;
            case DOWN:
                return down;
            case LEFT:
                return left;
            case RIGHT:
                return right;
            default:
                return null;
        }
    }
    
    /**
     * Sets an Edge connected to this Node.
     * @param edge the edge to set
     * @param direction the outgoing direction of the edge connected to this Node
     */
    public void setEdge(Edge edge, EDirection direction) {
        switch (direction) {
            case LEFT:
                left = edge;
                edge.setOrientation(EOrientation.HORIZONTAL);
                break;
            case RIGHT:
                right = edge;
                edge.setOrientation(EOrientation.HORIZONTAL);
                break;
            case UP:
                up = edge;
                edge.setOrientation(EOrientation.VERTICAL);
                break;
            case DOWN:
                down = edge;
                edge.setOrientation(EOrientation.VERTICAL);
                break;
        }
    }
    
    
    /**
     * Returns the possible turns to be performed on this Node.
     * @return the possible turns to be performed
     */
    public ArrayList<EDirection> getPossibleTurns() {
        ArrayList<EDirection> possible = new ArrayList<EDirection>();
        if (up != null) {
            possible.add(EDirection.UP);
        }
        if (down != null) {
            possible.add(EDirection.DOWN);
        }
        if (left != null) {
            possible.add(EDirection.LEFT);
        }
        if (right != null) {
            possible.add(EDirection.RIGHT);
        }
        return possible;
    
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Edge getLeft(){
        return left;
    }
    
    public Edge getRight(){
        return right;
    }
    
    public Edge getUp(){
        return up;
    }
    
    public Edge getDown(){
        return down;
    }
}
