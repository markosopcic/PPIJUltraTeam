package hr.fer.drinkinggame.generalobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.io.InputStream;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 03-May-18.
 */

public class Picture implements GameObject {

    public Bitmap picture;
    public Point p;

    public Picture(Bitmap d,Point pos){
        picture=d;
        p=pos;
    }

    public Picture(InputStream is, Point pos, int width, int height){
        picture = null;
        try {
            picture = BitmapFactory.decodeStream(is);
            picture = Bitmap.createScaledBitmap(picture, width, height, false);
        }
        catch (Exception e){
        }
        p=pos;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(picture,p.x,p.y,null);
    }

    @Override
    public void update() {

    }

    public Bitmap getPicture(){
        return picture;
    }

    public Point getPoint(){
        return p;
    }

    public void setPoint(Point p) {this.p = p;}

}
