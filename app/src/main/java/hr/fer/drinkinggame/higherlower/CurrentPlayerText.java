package hr.fer.drinkinggame.higherlower;

import android.graphics.Canvas;
import android.graphics.Paint;

import hr.fer.drinkinggame.GameObject;

public class CurrentPlayerText implements GameObject {

    private String text;
    private int width;

    public CurrentPlayerText(int width){
        this.width=width;
    }

    public void setCurrent(String name){
        text="Na potezu: "+name;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint pnt=new Paint();
        pnt.setTextSize(width/10);
        while(pnt.measureText(text)>width*8/10) pnt.setTextSize(pnt.getTextSize()-1);
        while(pnt.measureText(text)<width*8/10) pnt.setTextSize(pnt.getTextSize()+1);

        canvas.drawText(text,0,pnt.getTextSize(),pnt);
    }

    @Override
    public void update() {

    }
}
