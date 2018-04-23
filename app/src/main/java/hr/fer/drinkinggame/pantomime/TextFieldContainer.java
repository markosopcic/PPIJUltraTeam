package hr.fer.drinkinggame.pantomime;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 23-Mar-18.
 */

public class TextFieldContainer implements GameObject {

    Rect rect;

    public TextFieldContainer(int left, int top,  int right, int bottom) {
        rect = new Rect(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {

    }
}
