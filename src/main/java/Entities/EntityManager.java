package Entities;

import Game.Game;

public class EntityManager {

    public static void checkCollisions(Entity e) {
        if (e instanceof Pacman) {
            for (Ghost g : Game.gamestate().getGhosts()) {
                if ((g.getSpriteX() == e.getSpriteX()) || (g.getSpriteY() == e.getSpriteY())) {
                    if (areColliding(e, g)) {
                        g.onCollision(e);
                        e.onCollision(g);
                    }
                }
            }
            for (Food f : e.getCurrEdge().getFood()) {
                if(areColliding(e,f)){
                    e.onCollision(f);
                    f.onCollision(e);
                }
            }
        }
        if (e instanceof Ghost) {
            Pacman p = Game.gamestate().getPacman();
            if (e.getCurrEdge() == p.getCurrEdge()) {
                p.onCollision(e);
                e.onCollision(p);
            }
        }
    }

    public static boolean areColliding(Entity a, Entity b) {
        if (a.isColliding() && b.isColliding()) {
            if (a.getSpriteX() > b.getSpriteX() && ((a.getSpriteX() - a.getWidth()/2) - (b.getSpriteX()+b.getWidth()/2)) < 1) {
                return true;
            }
            if (a.getSpriteY() > b.getSpriteY() && ((a.getSpriteY() - a.getHeight()/2) - (b.getSpriteY()+b.getHeight()/2)) < 1) {
                return true;
            }
        }
        return false;
    }
}