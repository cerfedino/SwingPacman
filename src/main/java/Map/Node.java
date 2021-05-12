package Map;

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
    
    /////////////////////
    // Getters and Setters
    
    public void setEdge(Edge edge, EDirection direction) {
        switch (direction) {
            case LEFT:
                left = edge;
                break;
            case RIGHT:
                right = edge;
                break;
            case UP:
                up = edge;
                break;
            case DOWN:
                down = edge;
                break;
    
        }
        this.left=left;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
