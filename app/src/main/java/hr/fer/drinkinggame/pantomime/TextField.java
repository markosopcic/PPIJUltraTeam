package hr.fer.drinkinggame.pantomime;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 23-Mar-18.
 */

public class TextField implements GameObject {

    private String text;
    private TextPaint paint;
    private PointF point;

    public TextField(String text, TextPaint paint){
        this.text = text;
        this.paint = paint;
    }

    public TextField(String text, TextPaint paint, PointF point){
        this.text = text;
        this.paint = paint;
        this.point = point;
    }

    public String getText() {
        return text;
    }

    public float getTextWidth(String text){
        return this.paint.measureText(text);
    }

    public float getTextWidth(){
        return this.paint.measureText(this.text);
    }

    public void setText(String text){
        this.text = text;
    }

    public void setPoint(PointF point){
        this.point = point;
    }

    public PointF getPoint(){
        return this.point;
    }

    public void setColor(int color){
        this.paint.setColor(color);
    }

    public void scale(float width){
        if (paint.measureText(text)>width){
            for (float i = (int)paint.getTextSize(); paint.measureText(text) > width ; i--) {
                paint.setTextSize(i);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, point.x, point.y, paint);
    }

    @Override
    public void update() {

    }
}
