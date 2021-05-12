package Map;

/**
 * Represents a walkable path connect two edges together
 */
public class Edge {
    private final Node from;
    private final Node to;
    
    private final double lenght;
    
    // private ArrayList<Food> food;
    
    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
        
        lenght = calcLenght();
        
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
    
    public double getLenght(){
        return lenght;
    }
}
