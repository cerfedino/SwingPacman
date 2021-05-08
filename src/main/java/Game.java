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
        Sprite s = new Sprite(1000,0, EImage.ghost1);
        Sprite g = new Sprite(1000,0, EImage.ghost2);
        painter.registerSprite(s);
        painter.registerSprite(g);
        s.setX(0);
        painter.registerSprite(new Sprite(1000,200, EImage.pacman));
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    for (int z=0; z<=200; z++){
                        s.setPos(z,z);
                        g.setPos(z+100,z);
                        Thread.sleep(10);
                    }
                    for (int z=200; z<=400; z+=2){
                        s.setX(z);
                        g.setX(z+100);
                        Thread.sleep(10);
                    }
                    for (int z=200; z<=400; z+=4){
                        s.setY(z);
                        g.setY(z);
                        Thread.sleep(10);
                    }
                    for (int z=400; z<=600; z++){
                        s.setX(z);
                        g.setX(z+100);
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
