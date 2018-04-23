package hr.fer.drinkinggame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

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

    public boolean isButtonPressed(MotionEvent event){
        if(event.getX()>this.p.x && event.getX()<(this.p.x+this.button.getWidth()) && event.getY()>this.p.y && (event.getY()<this.p.y+this.button.getHeight())){
            return true;
        }
        return false;
    }
}
