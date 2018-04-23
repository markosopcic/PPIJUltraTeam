package hr.fer.drinkinggame.generalobjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 24-Apr-18.
 */

public class Line implements GameObject {
    Point start;
    Point end;
    Paint paint;

    public Line(int x1, int y1, int x2, int y2, Paint paint){
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
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
