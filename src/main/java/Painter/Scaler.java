package Painter;

/**
 * Scales an integer based on the ratio between the original screen size and the new one.
 * This way, all the settings and media get scaled in order for the game to run and look the same on every screen size.
 *
 */
public class Scaler {
    
    private static final int size = 1000;
    
    private static int newSize = 1000;
    private static double scale_factor = 1;
    
    /**
     * Scales an integer given in input
     * @param n the integer to scale
     * @return the scaled integer
     */
    public static int scale(int n) {
        return (int)(n * scale_factor);
    }
    
    /**
     * Sets the new size.
     * @param inSize the new size
     */
    public static void setNewsize (int inSize) {
        newSize = inSize;
        scale_factor = newSize/(double)size;
        System.out.println("**********\nSCALER\n" + "New size: "+newSize +" | Def size: "+size+" | -> Scale factor: "+scale_factor);
    }
    
    
    ////////////////////////////////
    /// Getters and Setters below
    
    public static double getScale_factor(){
        return scale_factor;
    }
    
    public static int getSize(){
        return size;
    }
    
    public static int getNewSize(){
        return newSize;
    }
}
