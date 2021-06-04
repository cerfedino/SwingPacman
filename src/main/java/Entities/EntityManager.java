package Entities;

import Game.Game;

/**
 * Handles and manages most of the Entites of the game.
 */
public class EntityManager {
    
    /**
     * Checks if the Sprite in inout has collided with any other entity in particular and
     *  if so calls their respective onCollision methods.
     * @param e the entity to check for collisions
     */
    public static void checkCollisions(Entity e) {
        if (e instanceof Pacman) {
            for (Food f : e.getCurrEdge().getFood()) {
                if(areColliding(e,f)) {
                    e.onCollision(f);
                    f.onCollision(e);
                }
            }
        }
        if (e instanceof Ghost) {
            Pacman p = Game.gamestate().getPacman();
            if (areColliding(e,p)) {
                p.onCollision(e);
                e.onCollision(p);
            }
        }
    }
    
    /**
     * Returns whether two Entities are colliding or not.
     * @param a the first Entity
     * @param b the second Entity
     * @return whether two Entities are colliding or not.
     */
    public static boolean areColliding(Entity a, Entity b) {
        double offset = 0.3;
        if (a.isColliding() && b.isColliding()) {
            int minX = Math.min(a.getSpriteX(),b.getSpriteX()); int minY = Math.min(a.getSpriteY(),b.getSpriteY());
            int maxX = Math.max(a.getSpriteX(),b.getSpriteX()); int maxY = Math.max(a.getSpriteY(),b.getSpriteY());
    
            if (maxX-minX < ((a.getWidth()/2) + (b.getWidth()/2))*offset
                && maxY-minY < ((a.getHeight()/2) + (b.getHeight()/2))*offset) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Makes all the Ghosts of the game vulnerable or not.
     * @param toVulnearble the new vulnerable value of the Ghosts
     */
    public static void makeGhostVulnerable(boolean toVulnearble) {
        for (Ghost g : Game.gamestate().getGhosts()) {
            g.setVulnerable(toVulnearble);
        }
    }
}