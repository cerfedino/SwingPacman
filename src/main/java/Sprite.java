import Media.EImage;

/**
 * A Sprite to be displayed on the game screen.
 */
public class Sprite {
    private int x;
    private int y;
    
    private EImage image;
    
    /**
     * Initializes a Sprite object.
     * @param x the x coordinate of the Sprite
     * @param y the y coordinate of the Sprite
     * @param image the EImage ENUM for the image
     */
    public Sprite(int x, int y, EImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
        
        Game.painter().registerSprite(this);
    }
    
    /**
     * Tells the painter to repaint the Sprite.
     */
    public void repaint() {
        Game.painter().repaintSprite(this);
    }
    
    /////////////////////////////
    // Getters and setters below
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public EImage getImage(){
        return image;
    }
    
    public void setImage(EImage image){
        this.image=image;
        Game.painter().repaintSprite(this);
    }
    
    public void setX(int x){
        this.x = x;
        Game.painter().repaintSprite(this);
    }
    
    public void setY(int y){
        this.y = y;
        Game.painter().repaintSprite(this);
    }
    
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
        Game.painter().repaintSprite(this);
    }
}
