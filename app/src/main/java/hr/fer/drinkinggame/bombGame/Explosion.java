package hr.fer.drinkinggame.bombGame;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import hr.fer.drinkinggame.GameActivity;
import hr.fer.drinkinggame.GameObject;

public class Explosion implements GameObject {

    private Bitmap expBitmap;
    private Point point;


    public Explosion(Bitmap expBitmap,Point point){
        this.expBitmap=expBitmap;
        this.point = point;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(expBitmap,point.x,point.y,null);
    }

    @Override
    public void update() {

    }

}

