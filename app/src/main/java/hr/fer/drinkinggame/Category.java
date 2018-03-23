package hr.fer.drinkinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

/**
 * Created by Niko on 23.3.2018..
 */

public class Category implements GameObject  {
    private String name;
    private Point point;

    public Category(String name, Point point) {
        this.name = name;
        this.point = point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public void draw(Canvas canvas) {
        //malo cudno ali radi

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText(name, 50, point.y, paint);



        //cuveni romanov static layout
        /*
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(160);
        textPaint.setColor(0xFF000000);

        int width = (int) textPaint.measureText(name);
        StaticLayout staticLayout = new StaticLayout(name, textPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
        staticLayout.draw(canvas);
        */
    }

    @Override
    public void update() {

    }
}
