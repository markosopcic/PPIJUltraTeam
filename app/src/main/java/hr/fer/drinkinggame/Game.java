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
    protected List<GameObject> gameObjects=new ArrayList<>();

    protected boolean finished=false;
  
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

    public boolean isFinished() {
        return finished;
    }

    public void pause(){}
}
