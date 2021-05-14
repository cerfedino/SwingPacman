package Map;

import java.util.ArrayList;

public class Node {
    private final int x;
    private final int y;
    
    private Edge left;
    private Edge right;
    private Edge up;
    private Edge down;
    
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
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
    
    
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
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
