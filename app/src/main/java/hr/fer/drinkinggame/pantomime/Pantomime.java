package hr.fer.drinkinggame.pantomime;

import hr.fer.drinkinggame.Button;
import hr.fer.drinkinggame.Game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import hr.fer.drinkinggame.MainThread;
import hr.fer.drinkinggame.generalobjects.Background;
import hr.fer.drinkinggame.generalobjects.Line;

/**
 * Created by roman on 23-Mar-18.
 */

public class Pantomime extends Game {
    private List<String> keyWords;
    private List<PantomimeButton> buttons;
    private MainThread thread;
    private boolean changedField;
    Context context;


    public Pantomime(Context context, DisplayMetrics dm, MainThread thread) {
        this.context = context;
        this.thread = thread;
        this.keyWords = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.keyWords.addAll(PantomimeKeyWords.getRandomKeyWords());
        this.changedField = false;

        AssetManager am = context.getAssets();
//        if(test){
//            Paint paint = new Paint();
//            paint.setColor(Color.RED);
//            float x = dm.widthPixels/2;
//            float y = dm.heightPixels/2;
//            setBackground(dm);
//            this.gameObjects.add(new Line(x,0, x,y*2, paint));
//            this.gameObjects.add(new Line(0, y, x*2,y, paint));
//            this.gameObjects.add(createButton(am,"images/iksde.png", (int)x, (int)y, 80, 80));

        TextPaint textPaint = initializeTextPaint(dm.density);
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        setBackground(dm);
        addKeyWords(am, dm, fm, textPaint);
        initializeClock(dm, fm, textPaint);

    }

    public void addKeyWords(AssetManager am, DisplayMetrics dm, Paint.FontMetrics fm, TextPaint textPaint){
        float density = dm.density;
        float x = dm.widthPixels/2;
        float y = dm.heightPixels/2;
        //(x,y) na pola ekrana
        float descent = fm.descent;
        float ascent = -fm.ascent;
        float marginTaB = 5 * density; // top and bottom margin

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        this.gameObjects.add(new Line(x,0, x,y*2, paint));
        this.gameObjects.add(new Line(0, y, x*2,y, paint));

//        this.gameObjects.add(new TextField("Ovo je proba", textPaint, new PointF(x-textPaint.measureText("Ovo je proba")/2, y)));
//        this.gameObjects.add(new Line(x-textPaint.measureText("Ovo je proba")/2, y, x-textPaint.measureText("Ovo je proba")/2, y-ascent, paint));

        float drawingWidth = x;
        float drawingHeight = (float) (y - descent * 2 - marginTaB * 2 - ascent * 2 + marginTaB/2);
        // x na pola, y na vrhu gjde krece zapis prvog
        for (String temp : this.keyWords){
            float textWidth = textPaint.measureText(temp);
            //y se mice prema dole jer pisanje texta krece iz lijevog donjeg kuta
            drawingHeight += ascent;

            TextField text = new TextField(temp, textPaint, new PointF(drawingWidth - textWidth/2, drawingHeight));
            this.gameObjects.add(text);

            //point u buttonu se nalazi u lijevom gornjem kutu
            int buttonWidth = (int)(ascent + descent);
            int buttonHeight = (int) (ascent+descent);
            Point buttonPoint = new Point((int)(drawingWidth + marginTaB * 2 + textWidth/2), (int)(drawingHeight - ascent));
            String image = "images/iksde.png";
            PantomimeButton button = new PantomimeButton(am, image, text, buttonWidth, buttonHeight, buttonPoint){
                public void doTheDeed(Pantomime game){
                    String temp = PantomimeKeyWords.getRandomKeyWord(game.keyWords);
                    float oldWidth = field.getTextWidth();
                    float newWidth = field.getTextWidth(temp);
                    this.field.setText(temp);
                    this.button.setPoint(new Point(this.button.getPoint().x + (int)(newWidth - oldWidth), this.button.getPoint().y));
                    this.field.setPoint(new PointF(this.field.getPoint().x + (int)(newWidth - oldWidth),this.field.getPoint().y));
                    game.changedField = true;
                }
            };
            this.gameObjects.add(button);
            this.buttons.add(button);

            drawingHeight += marginTaB + descent;
        }
    }

    private TextPaint initializeTextPaint(float density){
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(22 * density);
        textPaint.setColor(Color.WHITE);
        return textPaint;
    }

    private void initializeClock(DisplayMetrics dm, Paint.FontMetrics fm, TextPaint textPaint){
        float density = dm.density;

        float width = textPaint.measureText("10");
        float height = fm.descent - fm.ascent;
        float margin = 5 * density;
        float x = dm.widthPixels - margin - width;
        float y = margin + height/2;

        Clock clock = new Clock(10, textPaint, new PointF(x,y), this);
        this.gameObjects.add(clock);
        clock.start();
    }

    private void setBackground(DisplayMetrics dm){
        Background background = new Background(dm, Color.BLACK);
        this.gameObjects.add(background);
    }

    public void setRunning(Boolean running){
        thread.setRunning(running);
    }

    @Override
    public void handleTouch(MotionEvent event) {
        for(PantomimeButton button : this.buttons){
            if (button.isButtonPressed(event)){
                button.doTheDeed(this);
            }
        }
    }
}
