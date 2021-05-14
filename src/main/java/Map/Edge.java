package Map;

/**
 * Represents a walkable path connect two edges together
 */
public class Edge {
    private final Node from;
    private final Node to;
    
    private final double length;
    
    private EOrientation orientation;
    
    
    // private ArrayList<Food> food;
    
    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
        
        length = calcLenght();
        
        //TODO: Implement auto generation of food
    }
    
    private double calcLenght() {
        int x1 = from.getX();
        int y1 = from.getY();
    
        int x2 = to.getX();
        int y2 = to.getY();
        
        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }
    
    void spawnFood() {
        //TODO: Implement
    }
    
    //////////////
    // Getters and Setters below
    
    
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
}
