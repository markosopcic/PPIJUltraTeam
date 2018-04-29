package hr.fer.drinkinggame.generalobjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 24-Apr-18.
 */

public class Line implements GameObject {
    PointF start;
    PointF end;
    Paint paint;

    public Line(float x1, float y1, float x2, float y2, Paint paint){
        this.start = new PointF(x1, y1);
        this.end = new PointF(x2, y2);
        this.paint = paint;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine( start.x,  start.y,  end.x, end.y, paint);
    }

    @Override
    public void update() {

    }
}
