import Media.*;

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
        Sprite s1 = new Sprite(1000,200, EImage.ghost1);
        Sprite s2 = new Sprite(1000,200, EImage.ghost2);
        Sprite s3 = new Sprite(1000,200, EImage.ghost3);
        Sprite s4 = new Sprite(1000,200, EImage.ghost4);
        painter.registerSprite(s1);
        painter.registerSprite(s2);
        painter.registerSprite(s3);
        painter.registerSprite(s4);
        s1.setX(0);
        painter.registerSprite(new Sprite(1000,200, EImage.pacman));
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    for (int z=0; z<=200; z++){
                        s1.setPos(z,z);
                        s2.setPos(z+100,z);
                        s3.setPos(z,z+100);
                        s4.setPos(z+100,z+100);
                        Thread.sleep(10);
                    }
                    for (int z=200; z<=400; z+=2){
                        s1.setX(z);
                        s2.setX(z+100);
                        s3.setX(z);
                        s4.setX(z+100);
                        Thread.sleep(10);
                    }
                    for (int z=200; z<=400; z+=4){
                        s1.setY(z);
                        s2.setY(z);
                        s3.setY(z+100);
                        s4.setY(z+100);
                        Thread.sleep(10);
                    }
                    for (int z=400; z<=600; z++){
                        s1.setX(z);
                        s2.setX(z+100);
                        s3.setX(z);
                        s4.setX(z+100);
                        Thread.sleep(10);
                    }
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
