package hr.fer.drinkinggame.bombGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import hr.fer.drinkinggame.GameActivity;
import hr.fer.drinkinggame.GameObject;

public class Bomb implements GameObject {

    private Bitmap bombBitmap;
    private Point point;
    private int rotationDegrees = 0;



    public Bomb(Bitmap bombaBitmap,Point point){
        this.bombBitmap=bombaBitmap;
        this.point = point;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(bombBitmap,point.x,point.y,null);
    }

    @Override
    public void update() {

    }

}
