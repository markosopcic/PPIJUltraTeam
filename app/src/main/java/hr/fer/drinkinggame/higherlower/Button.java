package hr.fer.drinkinggame.higherlower;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import java.io.InputStream;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by Marko on 23.3.2018..
 */

public class Button implements GameObject {

    public Bitmap button;
    public Point p;

    public Button(Bitmap d,Point pos){
        button=d;
        p=pos;
    }

    public Button(InputStream is, Point pos, int width, int height){
        button = null;
        try {
            button = BitmapFactory.decodeStream(is);
            button = Bitmap.createScaledBitmap(button, width, height, false);
        }
        catch (Exception e){
        }
        p=pos;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(button,p.x,p.y,null);
    }

    @Override
    public void update() {

    }

    public Bitmap getButton(){
        return button;
    }

    public Point getPoint(){
        return p;
    }

    public void setPoint(Point p) {this.p = p;}

    public boolean isButtonPressed(MotionEvent event){
        if(event.getX()>this.p.x && event.getX()<(this.p.x+this.button.getWidth()) && event.getY()>this.p.y && (event.getY()<this.p.y+this.button.getHeight())){
            return true;
        }
        return false;
    }
}
