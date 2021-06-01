package Entities;

import Map.EDirection;
import Map.Edge;

import Media.EAudio;
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
        setLives((int)Settings.get(EParam.pacman_starting_lives));
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
        if (e instanceof Ghost) {
            if (!((Ghost)e).isVulnerable()) {
                if (lives > 1) {
                    Game.gamethread().performDeathSequence();
                    setLives(lives - 1);
                    System.out.println(lives);
                }
                else {
                    Game.gameOver();
                }
            } else {
                // The Ghost dies instead (add points)
            }
        }else if(e instanceof Food) {
            score += ((Food) e).getPoints();
            Game.painter().updateScoreLabel(score);
            if (e instanceof LargeFood) {
                Game.audioengine().playIfNotAlready(EAudio.large_food, null);
            } else{
                Game.audioengine().playIfNotAlready(EAudio.small_food, null);
            }
        }
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
    
    //////////////////
    // Getters and setters below
    
    public long getScore(){
        return score;
    }
    
    public void setLives(int lives){
        this.lives=lives;
        Game.painter().updateLivesPanel(lives);
    }
}
