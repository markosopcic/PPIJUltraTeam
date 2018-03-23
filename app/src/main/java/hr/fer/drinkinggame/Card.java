package hr.fer.drinkinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

/**
 * Created by Marko on 22.3.2018..
 */

public class Card implements GameObject {
    private Bitmap card;
    private String name;
    private Point point;

    public Card(Bitmap b,String n,Point p){
        card=b;
        name=n;
        point=p;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(card,point.x,point.y,new Paint());
    }

    @Override
    public void update() {

    }

    public void changePoint(Point newPoint){
        point=newPoint;
    }

    public Bitmap getCard(){
        return card;
    }
}
