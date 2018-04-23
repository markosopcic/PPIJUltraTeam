package hr.fer.drinkinggame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 23-Mar-18.
 */

public class Pantomime extends Game {
    private List<String> keyWords;

    public Pantomime(DisplayMetrics dm) {
        keyWords = new ArrayList<>();
        PantomimeKeyWords rici =  new PantomimeKeyWords();
        this.keyWords.addAll(rici.getRandomKeyWords());
        addKeyWords(dm);

    }

    public void addKeyWords(DisplayMetrics dm){
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(48);
        textPaint.setColor(Color.WHITE);
        Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();
        float density = dm.density;
        int rectHeight = ((fm.descent-fm .ascent) + 10);
        int rectWidth = 200;
        int x = dm.widthPixels/2 - rectWidth/2;
        int y = dm.heightPixels/2 - rectHeight - 10;
        for (String temp : keyWords){
            int left = x;
            int right = x + rectWidth;
            int top = y;
            int bottom = y + rectHeight;
            Rect rect = new Rect(left, top, right, bottom);
            int width = (int) textPaint.measureText(temp);
            TextField text = new TextField(temp, textPaint, width);
            Point point = new Point(left+(right-left)/2, top+(top-bottom)/2);
            text.setPoint(point);
            this.gameObjects.add(text);
            y=y + rectHeight + 20;
        }
    }

    @Override
    public void handleTouch(MotionEvent event) {
        
    }
}
