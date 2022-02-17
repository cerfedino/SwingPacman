package Map;

import Entities.MovingEntity;

import java.util.Random;

/**
 * A virtual path that connects two Nodes without taking in account physical space between them.
 */
// TODO: Make the MovingEntities not stop once they teleport but continue to move as if it was one long Edge
public class TeleportEdge extends Edge {
    
    /**
     * Initializes a TeleportEdge object.
     * @param from the starting Node
     * @param to the end Node
     */
    public TeleportEdge(Node from, Node to) {
        super(from,to);
        setLength(0);
    }
    
    /**
     * Returns the integer of the position after being teleported along the TeleportEdge.
     * @param direction the direction to move the coordinate along
     * @return the coordinate teleported along the Edge
     */
    @Override
    public int moveAlongEdge(EDirection direction, MovingEntity s) {
        Node targetNode = (getFrom().getTurn(direction) != this) ? getFrom():getTo();
        if(targetNode.canTurn(direction)) {
            Edge newedge = targetNode.getTurn(direction);
            s.setCurrEdge(newedge);
            if (getOrientation() == EOrientation.HORIZONTAL) {
                s.setX(targetNode.getX());
            } else
                s.setY(targetNode.getY());
            return newedge.moveAlongEdge(direction,s);
        }else {
            return getOrientation() == EOrientation.HORIZONTAL ? targetNode.getX() : targetNode.getY();
        }
    }
    
    /**
     * Gets the middle X coordinate of the Edge.
     * @return the middle X coordinate of the Edge.
     */
    @Override
    public int getMiddlePointX() {
        Random r = new Random();
        switch(getOrientation()) {
            case HORIZONTAL:
                switch(r.nextInt(1)) {
                    case 0:
                        return getFrom().getX();
                    case 1:
                        return getTo().getX();
                }
                break;
            case VERTICAL:
                return getFrom().getX();
        }
        return getFrom().getX();
    }
    
    /**
     * Gets the middle Y coordinate of the Edge.
     * @return the middle Y coordinate of the Edge.
     */
    @Override
    public int getMiddlePointY() {
        Random r = new Random();
        switch(getOrientation()) {
            case HORIZONTAL:
                return getFrom().getY();
            case VERTICAL:
                switch(r.nextInt(1)) {
                    case 0:
                        return getFrom().getY();
                    case 1:
                        return getTo().getY();
                }
                break;
                
        }
        return getFrom().getY();
    }
    
    @Override
    protected void spawnFood() {
    
    }
}
