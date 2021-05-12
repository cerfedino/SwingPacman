package Game;

import Entities.Sprite;
import Map.Map;
import Media.EImage;
import Painter.*;

import static java.lang.Thread.sleep;

/**
 * The main class that starts the game/
 */
public class Game {
    
    private static Painter painter;
    
    /**
     * The main method. Starts the game
     * @param args the arguments passed to the main method
     */
    public static void main(String[] args){
        
        ////////////////////////////
        //// DEMO
        painter = new Painter();
        Map map = new Map();
        painter.registerMap(map);
        
        Sprite s1 = new Sprite(Scaler.scale(0), Scaler.scale(0), EImage.ghost1);
        Sprite s2 = new Sprite(Scaler.scale(1000), Scaler.scale(0), EImage.ghost2);
        Sprite s3 = new Sprite(Scaler.scale(0), Scaler.scale(1000), EImage.ghost3);
        Sprite s4 = new Sprite(Scaler.scale(1000), Scaler.scale(1000), EImage.ghost4);
        
        painter.registerSprite(s1);
        painter.registerSprite(s2);
        painter.registerSprite(s3);
        painter.registerSprite(s4);
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    for (int z=0; z<=200; z++){
                        s1.setPos(z,z);
                        s2.setPos(z+60,z);
                        s3.setPos(z,z+60);
                        s4.setPos(z+60,z+60);
                        Thread.sleep(10);
                    }
                    for (int z=200; z<=400; z+=2){
                        s1.setX(z);
                        s2.setX(z+60);
                        s3.setX(z);
                        s4.setX(z+60);
                        Thread.sleep(10);
                    }
                    for (int z=200; z<=400; z+=4){
                        s1.setY(z);
                        s2.setY(z);
                        s3.setY(z+60);
                        s4.setY(z+60);
                        Thread.sleep(10);
                    }
                    for (int z=400; z<=600; z++){
                        s1.setX(z);
                        s2.setX(z+60);
                        s3.setX(z);
                        s4.setX(z+60);
                        Thread.sleep(10);
                    }
                    Thread.sleep(1000);
                    s1.removeSprite();
                    
                    Thread.sleep(1000);
                    s2.removeSprite();
                    
                    Thread.sleep(1000);
                    s3.removeSprite();
                    
                    Thread.sleep(1000);
                    s4.removeSprite();
    
                } catch (Exception ae){
    
                }
            }
        });
        t.start();
        //// END DEMO
        //////////////////////////////////
        
        
        
    }
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() {
        return painter;
    }
    
    
}
