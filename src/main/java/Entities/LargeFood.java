package Entities;

import AudioEngine.FunctionCallback;
import AudioEngine.PlaybackMode;

import Game.Game;
import Map.Edge;
import Media.EAudio;
import Media.EImage;
import Settings.*;

public class LargeFood extends Food {
    public LargeFood(int x, int y, Edge currEdge){
        super(x, y, EImage.large_food, currEdge, (int)Settings.get(EParam.large_food_score));
    }

    @Override
    public void onCollision(Entity e) {
        super.onCollision(e);
        
        Game.audioengine().playIfNotAlready(EAudio.large_food, PlaybackMode.regular, null);
        
        EntityManager.makeGhostVulnerable(true);
        Game.audioengine().restartOrPlay(EAudio.ghost_vulnerable, PlaybackMode.regular, new FunctionCallback() {
            @Override
            public void callback() {
                System.out.println("Finished ghost vulnerability");
                EntityManager.makeGhostVulnerable(false);
            }
        });
    }
}
