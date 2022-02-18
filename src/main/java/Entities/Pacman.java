package Entities;

import AudioEngine.AudioEngine;
import AudioEngine.FunctionCallback;
import AudioEngine.PlaybackMode;
import Game.Game;
import Map.EDirection;
import Map.Edge;
import Media.EAudio;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * The main character of the game, Pacman.
 */
public class Pacman extends MovingEntity{
    
    private int lives;
    private long score = 0;
    private int streak = 1;
    
    /**
     * Initializes a Pacman object.
     * @param location the Edge where the pacman is located.
     */
    public Pacman(Edge location){
        super(null, location, null, (int)Settings.get(EParam.pacman_speed));
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
                    Game.gamestate().setRound(Game.gamestate().getRound() + 1);
                    System.out.println(lives);
                }
                else {
                    Game.gameOver();
                }
            } else {
                // TODO: Not completely implementing the printing of the increased score above Pacmans head
                Game.gamethread().freezeEntities();
                JLabel label = new JLabel("1000");
                label.setForeground(Color.red);
                label.setFont(new Font("Serif", Font.BOLD, 35));
                ((JPanel) e).add(label);
                AudioEngine.play(EAudio.ghost_ate, PlaybackMode.regular, new FunctionCallback() {
                    @Override
                    public void callback(){
                        e.remove(label);
                        Game.gamethread().unfreezeEntities();
                    }
                });
                ((Ghost) e).die();
                increaseScore((int)Settings.get(EParam.ghost_vuln_val));
            }
        }else if(e instanceof Food) {
            score += ((Food) e).getPoints();
            Game.painter().updateScoreLabel(score);
            if (e instanceof LargeFood) {
                AudioEngine.playIfNotAlready(EAudio.large_food, PlaybackMode.regular, null);
            } else{
                AudioEngine.playIfNotAlready(EAudio.small_food, PlaybackMode.regular,null);
            }
        }
    }
    
    /**
     * Handles the decision-making when it comes to choosing which turn to perform next.
     * @param direction used by Pacman to manually add a turn
     */
    @Override
    public void addTurn(EDirection direction) {
        if (getDirection() != null) {
            // If Pacman tries to invert its direction, it immediately does so
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
        // Otherwise adds the turn to the queue
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
    
    public void increaseScore(long score) {
        setScore(this.score + score);
    }
    
    public void setScore(long score){
        this.score = score;
        Game.painter().updateScoreLabel(score);
    }
}
