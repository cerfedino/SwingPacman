package Entities;

import Map.Edge;
import Media.EImage;
import Settings.*;

import java.util.Random;

public class Ghost extends MovingEntity{
    
    public Ghost(Edge location){
        super(EImage.ghost1,location,(int)Settings.get(EParam.ghost_speed));
        
        // Chooses a random image for the ghost
        EImage img;
        switch(new Random().nextInt(4)+1) {
            case 2:
                img = EImage.ghost2;
                break;
            case 3:
                img = EImage.ghost3;
                break;
            case 4:
                img = EImage.ghost4;
                break;
            default:
                img = EImage.ghost1;
                break;
        }
        setImage(img);
    }
    
}
