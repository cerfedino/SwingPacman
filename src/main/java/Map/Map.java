package Map;

import Painter.Scaler;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The Map navigable by MovingEntity objects and to be independently painted on the game screen.
 */
public class Map extends JPanel {
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;
    
    /**
     * Initializes a Map object.
     */
    public Map() {
        createMap();
        setBounds(0,0,Scaler.getNewSize(),Scaler.getNewSize());
        setOpaque(false);
    }
    
    /**
     * Paints the map in its swing Container.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor((Color)Settings.get(EParam.line_color));
        int line_thickness = (int)Settings.get(EParam.line_thickness);
        
        ((Graphics2D) g).setStroke(new BasicStroke(line_thickness));
        
        //Paints all the edges
        int offset = ((int)Settings.get(EParam.path_width)/2);
        for (Edge e : edges) {
            // Paints only edges that are not TeleportEdge
            if(!(e instanceof TeleportEdge)){
                Node n1=e.getFrom();
                Node n2=e.getTo();
                
                
                int x1=n1.getX();
                int y1=n1.getY();
                int x2=n2.getX();
                int y2=n2.getY();
    
    
                switch (e.getOrientation()){
                    case HORIZONTAL:
                        g.drawLine(x1+offset, y1-offset, x2-offset, y2-offset);
                        g.drawLine(x1+offset, y1+offset, x2-offset, y2+offset);
                        break;
                    case VERTICAL:
                        g.drawLine(x1-offset, y1+offset, x2-offset, y2-offset);
                        g.drawLine(x1+offset, y1+offset, x2+offset, y2-offset);
                        break;
                }
            }
        }
        
        // Paints all the nodes
        for (Node n : nodes) {
            int x = n.getX() - offset;
            int y = n.getY() - offset;
            int line_lenght = (int)Settings.get(EParam.path_width) - line_thickness;

            if (n.getLeft() == null) {
                g.drawLine(x,y+line_thickness,x,y+line_lenght);
            }
            if (n.getRight() == null) {
                g.drawLine(x+offset*2,y+line_thickness,x+offset*2,y+line_lenght);
            }
            if (n.getUp() == null) {
                g.drawLine(x+line_thickness,y,x+line_lenght,y);
            }
            if (n.getDown() == null) {
                g.drawLine(x+line_thickness,y+offset*2,x+line_lenght,y+offset*2);
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Adds an Edge connecting two Nodes
     * @param from the starting node
     * @param to the end node
     * @param fromDirection the direction of the new Edge from the start Node
     * @param toDirection the direction of the new Edge from the end Node
     */
    private void addEdge(Node from, Node to, EDirection fromDirection, EDirection toDirection) {
        if (!nodes.contains(from))
            nodes.add(from);
        if (!nodes.contains(to))
            nodes.add(to);
        
        Edge edge = new Edge(from, to);
        from.setEdge(edge, fromDirection);
        to.setEdge(edge, toDirection);
        edges.add(edge);
    }
    
    /**
     * Adds a TeleportEdge connecting two Nodes.
     * @param from the starting node
     * @param to the end node
     * @param fromDirection the direction of the new Edge from the start Node
     * @param toDirection the direction of the new Edge from the end Node
     */
    private void addTeleportEdge(Node from, Node to, EDirection fromDirection, EDirection toDirection) {
        if (!nodes.contains(from))
            nodes.add(from);
        if (!nodes.contains(to))
            nodes.add(to);
        
        TeleportEdge edge = new TeleportEdge(from, to);
        from.setEdge(edge, fromDirection);
        to.setEdge(edge, toDirection);
        edges.add(edge);
    }
    
    /**
     * Creates the Map by creating all the required Edges and Nodes.
     */
    private void createMap() {
        edges = new ArrayList<Edge>();
        nodes = new ArrayList<Node>();

        Node r1c1 = new Node(Scaler.scale(50), Scaler.scale(50));
        Node r1c2 = new Node(Scaler.scale(250), Scaler.scale(50));
        addEdge(r1c1, r1c2, EDirection.RIGHT, EDirection.LEFT);

        Node r1c4 = new Node(Scaler.scale(470), Scaler.scale(50));
        addEdge(r1c2, r1c4, EDirection.RIGHT, EDirection.LEFT);

        Node r2c1 = new Node(Scaler.scale(50), Scaler.scale(200));
        addEdge(r1c1, r2c1, EDirection.DOWN, EDirection.UP);

        Node r2c2 = new Node(Scaler.scale(250), Scaler.scale(200));
        addEdge(r2c1, r2c2, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r1c2, r2c2, EDirection.DOWN, EDirection.UP);

        Node r2c3 = new Node(Scaler.scale(360), Scaler.scale(200));
        addEdge(r2c2, r2c3, EDirection.RIGHT, EDirection.LEFT);

        Node r2c4 = new Node(Scaler.scale(470), Scaler.scale(200));
        addEdge(r2c3, r2c4, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r1c4, r2c4, EDirection.DOWN, EDirection.UP);

        Node r3c1 = new Node(Scaler.scale(50), Scaler.scale(300));
        addEdge(r2c1, r3c1, EDirection.DOWN, EDirection.UP);

        Node r3c2 = new Node(Scaler.scale(250), Scaler.scale(300));
        addEdge(r3c1, r3c2, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r2c2, r3c2, EDirection.DOWN, EDirection.UP);

        Node r3c3 = new Node(Scaler.scale(360), Scaler.scale(300));
        addEdge(r2c3,r3c3, EDirection.DOWN, EDirection.UP);

        Node r3c4 = new Node(Scaler.scale(470), Scaler.scale(300));
        addEdge(r3c3, r3c4, EDirection.RIGHT, EDirection.LEFT);

        Node r1c5 = new Node(Scaler.scale(530), Scaler.scale(50));
        Node r1c7 = new Node(Scaler.scale(750), Scaler.scale(50));
        addEdge(r1c5, r1c7, EDirection.RIGHT, EDirection.LEFT);

        Node r1c8 = new Node(Scaler.scale(950), Scaler.scale(50));
        addEdge(r1c7, r1c8, EDirection.RIGHT, EDirection.LEFT);

        Node r2c5 = new Node(Scaler.scale(530), Scaler.scale(200));
        addEdge(r2c4, r2c5, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r1c5, r2c5, EDirection.DOWN, EDirection.UP);

        Node r2c6 = new Node(Scaler.scale(640), Scaler.scale(200));
        addEdge(r2c5, r2c6, EDirection.RIGHT, EDirection.LEFT);

        Node r2c7 = new Node(Scaler.scale(750), Scaler.scale(200));
        addEdge(r2c6, r2c7, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r1c7, r2c7, EDirection.DOWN, EDirection.UP);

        Node r2c8 = new Node(Scaler.scale(950), Scaler.scale(200));
        addEdge(r2c7, r2c8, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r1c8, r2c8, EDirection.DOWN, EDirection.UP);

        Node r3c5 = new Node(Scaler.scale(530), Scaler.scale(300));
        Node r3c6 = new Node(Scaler.scale(640), Scaler.scale(300));
        addEdge(r3c5, r3c6, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r2c6, r3c6, EDirection.DOWN, EDirection.UP);

        Node r3c7 = new Node(Scaler.scale(750), Scaler.scale(300));
        addEdge(r2c7, r3c7, EDirection.DOWN, EDirection.UP);

        Node r3c8 = new Node(Scaler.scale(950), Scaler.scale(300));
        addEdge(r3c7, r3c8, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r2c8, r3c8, EDirection.DOWN, EDirection.UP);

        Node r4c3 = new Node(Scaler.scale(360), Scaler.scale(400));
        Node r4c4 = new Node(Scaler.scale(470), Scaler.scale(400));
        addEdge(r4c3, r4c4, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r3c4, r4c4, EDirection.DOWN, EDirection.UP);

        Node r4c5 = new Node(Scaler.scale(530), Scaler.scale(400));
        addEdge(r4c4, r4c5, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r3c5, r4c5, EDirection.DOWN, EDirection.UP);

        Node r4c6 = new Node(Scaler.scale(640), Scaler.scale(400));
        addEdge(r4c5, r4c6, EDirection.RIGHT, EDirection.LEFT);

        Node r5c2 = new Node(Scaler.scale(250), Scaler.scale(500));
        addEdge(r3c2, r5c2, EDirection.DOWN, EDirection.UP);

        Node r5c3 = new Node(Scaler.scale(360), Scaler.scale(500));
        addEdge(r5c2, r5c3, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r4c3, r5c3, EDirection.DOWN, EDirection.UP);

        Node r5c6 = new Node(Scaler.scale(640), Scaler.scale(500));
        addEdge(r4c6, r5c6, EDirection.DOWN, EDirection.UP);

        Node r5c7 = new Node(Scaler.scale(750), Scaler.scale(500));
        addEdge(r5c6, r5c7, EDirection.RIGHT, EDirection.LEFT);

        Node empty1 = new Node(Scaler.scale(0), Scaler.scale(500));
        ///
        addEdge(empty1, r5c2, EDirection.RIGHT, EDirection.LEFT);

        Node empty2 = new Node(Scaler.scale(1000), Scaler.scale(500));
        ////
        addEdge(r5c7, empty2, EDirection.RIGHT, EDirection.LEFT);
        addTeleportEdge(empty2,empty1, EDirection.RIGHT, EDirection.LEFT);
        addTeleportEdge(empty1,empty2, EDirection.LEFT, EDirection.RIGHT);
        addEdge(r3c7, r5c7, EDirection.DOWN, EDirection.UP);

        Node r6c3 = new Node(Scaler.scale(360),Scaler.scale(600));
        addEdge(r5c3, r6c3, EDirection.DOWN, EDirection.UP);

        Node r6c6 = new Node(Scaler.scale(640), Scaler.scale(600));
        addEdge(r6c3, r6c6, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r5c6, r6c6, EDirection.DOWN, EDirection.UP);

        Node r7c1 = new Node(Scaler.scale(50),Scaler.scale(700));
        Node r7c2 = new Node(Scaler.scale(250), Scaler.scale(700));
        addEdge(r7c1, r7c2, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r5c2, r7c2, EDirection.DOWN, EDirection.UP);

        Node r7c3 = new Node(Scaler.scale(360), Scaler.scale(700));
        addEdge(r7c2, r7c3, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r6c3, r7c3, EDirection.DOWN, EDirection.UP);

        Node r7c4 = new Node(Scaler.scale(470), Scaler.scale(700));
        addEdge(r7c3, r7c4, EDirection.RIGHT, EDirection.LEFT);

        Node r7c5 = new Node(Scaler.scale(530), Scaler.scale(700));
        Node r7c6 = new Node(Scaler.scale(640), Scaler.scale(700));
        addEdge(r7c5, r7c6, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r6c6, r7c6, EDirection.DOWN, EDirection.UP);

        Node r7c7 = new Node(Scaler.scale(750), Scaler.scale(700));
        addEdge(r7c6, r7c7, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r5c7, r7c7, EDirection.DOWN, EDirection.UP);

        Node r7c8 = new Node(Scaler.scale(950), Scaler.scale(700));
        addEdge(r7c7, r7c8, EDirection.RIGHT, EDirection.LEFT);

        Node r8c1 = new Node(Scaler.scale(50), Scaler.scale(780));
        Node r8c2_half = new Node(Scaler.scale(150), Scaler.scale(780));
        addEdge(r8c1, r8c2_half, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r7c1, r8c1, EDirection.DOWN, EDirection.UP);

        Node r8c2 = new Node(Scaler.scale(250), Scaler.scale(780));
        addEdge(r7c2, r8c2, EDirection.DOWN, EDirection.UP);

        Node r8c3 = new Node(Scaler.scale(360), Scaler.scale(780));
        addEdge(r8c2, r8c3, EDirection.RIGHT, EDirection.LEFT);

        Node r8c4 = new Node(Scaler.scale(470), Scaler.scale(780));
        addEdge(r8c3, r8c4, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r7c4, r8c4, EDirection.DOWN, EDirection.UP);

        Node r8c5 = new Node(Scaler.scale(530), Scaler.scale(780));
        addEdge(r8c4, r8c5, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r7c5, r8c5, EDirection.DOWN, EDirection.UP);

        Node r8c6 = new Node(Scaler.scale(640), Scaler.scale(780));
        addEdge(r8c5, r8c6, EDirection.RIGHT, EDirection.LEFT);

        Node r8c7 = new Node(Scaler.scale(750), Scaler.scale(780));
        addEdge(r8c6, r8c7, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r7c7, r8c7, EDirection.DOWN, EDirection.UP);

        Node r8c7_half = new Node(Scaler.scale(850), Scaler.scale(780));
        Node r8c8 = new Node(Scaler.scale(950), Scaler.scale(780));
        addEdge(r8c7_half, r8c8, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r7c8, r8c8, EDirection.DOWN, EDirection.UP);

        Node r9c1 = new Node(Scaler.scale(50), Scaler.scale(880));
        Node r9c2_half = new Node(Scaler.scale(150), Scaler.scale(880));
        addEdge(r9c1, r9c2_half, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r8c2_half, r9c2_half, EDirection.DOWN, EDirection.UP);

        Node r9c2 = new Node(Scaler.scale(250), Scaler.scale(880));
        addEdge(r9c2_half, r9c2, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r8c2, r9c2, EDirection.DOWN, EDirection.UP);

        Node r9c3 = new Node(Scaler.scale(360), Scaler.scale(880));
        addEdge(r8c3, r9c3, EDirection.DOWN, EDirection.UP);

        Node r9c4 = new Node(Scaler.scale(470), Scaler.scale(880));
        addEdge(r9c3, r9c4, EDirection.RIGHT, EDirection.LEFT);

        Node r9c5 = new Node(Scaler.scale(530), Scaler.scale(880));
        Node r9c6 = new Node(Scaler.scale(640), Scaler.scale(880));
        addEdge(r9c5, r9c6, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r8c6, r9c6, EDirection.DOWN, EDirection.UP);

        Node r9c7 = new Node(Scaler.scale(750), Scaler.scale(880));
        addEdge(r8c7, r9c7, EDirection.DOWN, EDirection.UP);

        Node r9c7_half = new Node(Scaler.scale(850), Scaler.scale(880));
        addEdge(r9c7, r9c7_half, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r8c7_half, r9c7_half, EDirection.DOWN, EDirection.UP);

        Node r9c8 = new Node(Scaler.scale(950), Scaler.scale(880));
        addEdge(r9c7_half, r9c8, EDirection.RIGHT, EDirection.LEFT);

        Node r10c1 = new Node(Scaler.scale(50), Scaler.scale(950));
        addEdge(r9c1, r10c1, EDirection.DOWN, EDirection.UP);

        Node r10c4 = new Node(Scaler.scale(470), Scaler.scale(950));
        addEdge(r9c4, r10c4, EDirection.DOWN, EDirection.UP);
        addEdge(r10c1, r10c4, EDirection.RIGHT, EDirection.LEFT);

        Node r10c5 = new Node(Scaler.scale(530), Scaler.scale(950));
        addEdge(r10c4, r10c5, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r9c5, r10c5, EDirection.DOWN, EDirection.UP);

        Node r10c8 = new Node(Scaler.scale(950), Scaler.scale(950));
        addEdge(r10c5, r10c8, EDirection.RIGHT, EDirection.LEFT);
        addEdge(r9c8, r10c8, EDirection.DOWN, EDirection.UP);

    }
    
    //////////////////
    // Getters and Setters below
    
    public ArrayList<Edge> getEdges(){
        return edges;
    }
}
