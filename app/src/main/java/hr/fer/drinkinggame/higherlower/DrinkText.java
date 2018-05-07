package hr.fer.drinkinggame.higherlower;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import hr.fer.drinkinggame.GameObject;

public class DrinkText implements GameObject {

    private String text="";
    private Point p=new Point();
    private int width;
    private Paint pnt;
    public DrinkText(int width, int y){
        this.width=width;
        p.y=y;
        pnt=new Paint();
        pnt.setColor(Color.RED);
        pnt.setTextSize(width/10);
    }

    public void setDrinking(String name,String text){
        this.text=name+" "+text;
        p.x=width/2-(int)pnt.measureText(this.text)/2;

    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawText(text,p.x,p.y,pnt);

    }



    @Override
    public void update() {

    }
}
