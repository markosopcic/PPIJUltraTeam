package hr.fer.drinkinggame.generalobjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 23-Apr-18.
 */

public class Background implements GameObject {
    public Rect rect;
    public Paint paint;

    public Background(DisplayMetrics dm, int color) {
        this.rect = new Rect(0,0, dm.widthPixels, dm.heightPixels);
        this.paint = new Paint();
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.FILL);
    };

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {

    }
}
