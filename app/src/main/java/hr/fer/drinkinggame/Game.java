package hr.fer.drinkinggame;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 21.3.2018..
 */

public abstract class Game implements GameObject{
    List<GameObject> gameObjects=new ArrayList<>();


    public void draw(Canvas canvas){
       for(GameObject obj:gameObjects){
            obj.draw(canvas);
        }
    }

    public void update(){
          for(GameObject obj:gameObjects){
            obj.update();
        }
    }
}
