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
        painter = new Painter();
        Sprite s = new Sprite(1000,0, EImage.pacman);
        painter.registerSprite(s);
        s.setX(0);
        
        painter.registerSprite(new Sprite(1000,0, EImage.ghost));
        
        //TODO: This lags way too much
        Thread t = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
    
                    for (int z=0; z>=0; z++){
                        s.setX(z);
                        Thread.sleep(60);
                    }
                } catch (Exception ae){
    
                }
            }
        });
        
        t.start();
        
        
        
        
    }
    
    //////////////////////////
    // Getters and Setters below
    
    public static Painter painter() {
        return painter;
    }
}
