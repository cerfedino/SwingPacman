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
