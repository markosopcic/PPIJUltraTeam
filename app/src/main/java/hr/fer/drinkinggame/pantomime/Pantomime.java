package hr.fer.drinkinggame.pantomime;

import hr.fer.drinkinggame.Game;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import hr.fer.drinkinggame.generalobjects.Background;
import hr.fer.drinkinggame.generalobjects.Line;

/**
 * Created by roman on 23-Mar-18.
 */

public class Pantomime extends Game {
    private List<String> keyWords;

    public Pantomime(DisplayMetrics dm) {
        keyWords = new ArrayList<>();
        PantomimeKeyWords rici =  new PantomimeKeyWords();
        this.keyWords.addAll(rici.getRandomKeyWords());
        setBackground(dm);
        addKeyWords(dm);
    }

    public void addKeyWords(DisplayMetrics dm){

        TextPaint textPaint = initializeTextPaint();

        Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();
        float density = dm.density;
        int rectHeight = ((fm.descent-fm .ascent) + 10);
        int rectWidth = 200;
        int x = dm.widthPixels/2;
        int y = dm.heightPixels/2;
        y -= (9/4)*rectHeight;
        for (String temp : keyWords){
            int width = (int) textPaint.measureText(temp);

            //int left = x - rectWidth/2;
            //int right = x + rectWidth/2;
            //int top = y - rectHeight/2;
            //int bottom = y + rectHeight/2;
            //TextFieldContainer container = new TextFieldContainer(left, top, right, bottom);
            //this.gameObjects.add(container);

            TextField text = new TextField(temp, textPaint, width);
            Point point = new Point(x - width/2, y - 32 );
            text.setPoint(point);
            this.gameObjects.add(text);

            Paint linePaint = new Paint();
            linePaint.setColor(Color.RED);
            this.gameObjects.add(new Line(x+10+width/2,y+32,x+30+width/2,y-32, linePaint));
            this.gameObjects.add(new Line(x+10+width/2,y-32,x+30+width/2,y+32, linePaint));

            y = y + rectHeight + rectHeight/2;
        }
    }

    private TextPaint initializeTextPaint(){
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(64);
        textPaint.setColor(Color.WHITE);
        return textPaint;
    }

    private void setBackground(DisplayMetrics dm){
        Background background = new Background(dm, Color.BLACK);
        this.gameObjects.add(background);
    }

    @Override
    public void handleTouch(MotionEvent event) {

    }
}
