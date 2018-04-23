package hr.fer.drinkinggame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marko on 21.3.2018..
 */

public abstract class Game implements GameObject{
    List<GameObject> gameObjects=new ArrayList<>();


    public void draw(Canvas canvas){
        List<GameObject> toDraw=new ArrayList<>(gameObjects);
       for(GameObject obj:toDraw){
            obj.draw(canvas);
        }
    }

    public void update(){
        List<GameObject> toUpdate=new ArrayList<>(gameObjects);
          for(GameObject obj:toUpdate){
            obj.update();
        }
    }

    public abstract void handleTouch(MotionEvent event);
}
