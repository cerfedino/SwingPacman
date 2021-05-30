package Entities;

import Map.EDirection;
import Map.Edge;

import Media.EImage;
import Settings.EParam;
import Settings.Settings;
import Game.*;

/**
 * An enemy Ghost.
 */
public class Pacman extends MovingEntity{
    
    private int lives;
    private long score = 0;
    
    /**
     * Initializes a Ghost object.
     * @param location the Edge where the ghost is located.
     */
    public Pacman(Edge location){
        super(EImage.pacman, location, null, (int)Settings.get(EParam.pacman_speed));
        lives = (int)Settings.get(EParam.pacman_starting_lives);
    }
    
    /**
     * Handles the complete removal of the Pacman from the game.
     */
    @Override
    public void removeSprite() {
        super.removeSprite();
        Game.gamestate().removePacman(this);
    }
    
    /**
     * To be called when the Pacman collides with another Entity.
     * @param e the Entity the Pacman collided with
     */
    @Override
    public void onCollision(Entity e) {
    
    }
    @Override
    public void step() {
        super.step();
    }
    /**
     * Handles the decision-making when it comes to choosing which turn to perform next.
     * @param direction used by Pacman to manually add a turn
     */
    @Override
    public void addTurn(EDirection direction) {
        if (getDirection() != null) {
            switch (getDirection()) {
                case DOWN:
                    if (direction == EDirection.UP) {
                        setDirection(direction);
                        return;
                    }
                    break;
                case LEFT:
                    if (direction == EDirection.RIGHT) {
                        setDirection(direction);
                        return;
                    }
                    break;
                case UP:
                    if (direction == EDirection.DOWN) {
                        setDirection(direction);
                        return;
                    }
                    break;
                case RIGHT:
                    if (direction == EDirection.LEFT) {
                        setDirection(direction);
                        return;
                    }
                    break;
            }
            
        }
        if (direction != null){
            turnQueue.addFirst(direction);
            if (turnQueue.size() > 1) {
                turnQueue.removeLast();
            }
        }
    }
    
}
