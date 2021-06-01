package Entities;

import Game.Game;

public class EntityManager {

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

    public static void makeGhostVulnerable(boolean toVulnearble) {
        for (Ghost g : Game.gamestate().getGhosts()) {
            System.out.println("Ghost is now vulnerable");
            g.setVulnerable(toVulnearble);
        }
    }
}