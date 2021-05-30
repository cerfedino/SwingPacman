package Entities;


import Game.Game;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class EntityManager {

    public static void checkCollisions(Entity e) {
        if (e instanceof Pacman) {
            for (Ghost g : Game.gamestate().getGhosts()) {
                if ((g.getSpriteX() == e.getSpriteX()) || (g.getSpriteY() == e.getSpriteY())) {
                    checkCollision(e, g);
                    checkCollision(g, e);
                }
            }
            // check for food collision as well
        }
        if (e instanceof Ghost) {
            Pacman p = Game.gamestate().getPacman();
            if (e.getCurrEdge() == p.getCurrEdge()) {
                checkCollision(p, e);
                checkCollision(e, p);
            }
        }
    }

    public static void checkCollision(Entity a, Entity b) {
        if (a.isColliding() && b.isColliding()) {
            if (a.getSpriteX() > b.getSpriteX()) {
                if (((a.getSpriteX() - a.getWidth()/2) - (b.getSpriteX()+b.getWidth()/2)) < 1) {
                    System.out.println("Collision");
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
            if (a.getSpriteY() > b.getSpriteY()) {
                if (((a.getSpriteY() - a.getHeight()/2) - (b.getSpriteY()+b.getHeight()/2)) < 1) {
                    System.out.println("Collision");
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }
    }
}