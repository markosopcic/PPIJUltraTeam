package hr.fer.drinkinggame.pantomime;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 23-Mar-18.
 */

public class TextField extends StaticLayout implements GameObject {

    final static boolean includepad = false;
    final static float spacingadd = 0;
    final static float spacingmult = 1.0f;
    final static Layout.Alignment align = Layout.Alignment.ALIGN_CENTER;
    private Point point;

    public TextField(CharSequence source, TextPaint paint, int width){
        super(source, paint, width, align, spacingmult, spacingadd, includepad);
    }


    @Override
    public void draw(Canvas canvas) {

        canvas.save();
        canvas.translate(point.x, point.y);
        super.draw(canvas);
        canvas.restore();

    }

    public void setPoint(Point point){
        this.point = point;
    }

    @Override
    public void update() {

    }
}
