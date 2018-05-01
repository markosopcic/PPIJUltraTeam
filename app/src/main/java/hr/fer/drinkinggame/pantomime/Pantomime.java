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
    private List<Button> buttons;
    private MainThread thread;
    private AssetManager am;
    private boolean changedField;
    Context context;


    public Pantomime(Context context, DisplayMetrics dm, MainThread thread) {
        this.context = context;
        this.thread = thread;
        this.keyWords = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.keyWords.addAll(PantomimeKeyWords.getRandomKeyWords());
        this.changedField = false;

        am = context.getAssets();
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
        float drawingHeight = addTextFieldsAndButtons(am, dm, fm, textPaint);
        Clock clock = initializeClock(dm, fm, textPaint);
        createStartButton(am, dm, drawingHeight, clock);
    }

    public float addTextFieldsAndButtons(AssetManager am, DisplayMetrics dm, Paint.FontMetrics fm, TextPaint textPaint){
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
            Button button = null;
            try{
            button = new TextChangeButton(am.open("images/iksde.png"), buttonWidth, buttonHeight, buttonPoint, text);}
            catch (Exception e){
            }
            this.gameObjects.add(button);
            this.buttons.add(button);

            drawingHeight += marginTaB + descent;
        }
        return drawingHeight;
    }

    private TextPaint initializeTextPaint(float density){
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(22 * density);
        textPaint.setColor(Color.WHITE);
        return textPaint;
    }

    private Clock initializeClock(DisplayMetrics dm, Paint.FontMetrics fm, TextPaint textPaint){
        float density = dm.density;

        float width = textPaint.measureText("10");
        float height = fm.descent - fm.ascent;
        float margin = 5 * density;
        float x = dm.widthPixels - margin - width;
        float y = margin + height/2;

        Clock clock = new Clock(60, textPaint, new PointF(x,y), this);
        this.gameObjects.add(clock);
        return clock;
    }

    private void createStartButton(AssetManager am, DisplayMetrics dm, float drawingHeight, Clock clock){
        float x = dm.widthPixels/2;
        float y = dm.heightPixels/2;
        float startButtonWidth = x;
        float startButtonHeight = ((y*2) - drawingHeight)/2;
        Point startButtonPoint = new Point((int)(x - startButtonWidth/2),(int) (drawingHeight + startButtonHeight/2));
        Button startButton = null;
        try{
            startButton = new StartButton(am.open("images/startde.png"),startButtonPoint, (int)startButtonWidth, (int)startButtonHeight, clock);}
        catch (Exception e){
        }
        this.buttons.add(startButton);
        this.gameObjects.add(startButton);
    }

    private void getRidOfTextChangeButtons(){
        List<Button> tempButtons = new ArrayList<>();
        for(Button temp : buttons){
            if (temp instanceof TextChangeButton)
                tempButtons.add(temp);
        }
        for(Button temp : tempButtons){
            buttons.remove(temp);
            gameObjects.remove(temp);
            try {
                buttons.add(new TextGuessedButton(am.open("images/yesde.png"), ((TextChangeButton)temp)));
            }
            catch (Exception e){
            }
        }
    }

    private void getRidOfTextGuessedButtons(){
        List<Button> tempButtons = new ArrayList<>();
        for(Button temp : buttons){
            if (temp instanceof TextGuessedButton)
                tempButtons.add(temp);
        }
        for(Button temp : tempButtons){
            buttons.remove(temp);
            gameObjects.remove(temp);
        }
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
        for(Button button : this.buttons){
            if (button.isButtonPressed(event)) {
                if (button instanceof TextChangeButton) {
//                    if(changedField)
//                        break;
//                    changedField = true;
                    String temp = PantomimeKeyWords.getRandomKeyWord(keyWords);
                    TextField text = ((TextChangeButton) button).text;
                    float oldWidth = text.getTextWidth();
                    float newWidth = text.getTextWidth(temp);
                    text.setText(temp);

                    PointF oldTextPoint = text.getPoint();
                    text.setPoint(new PointF(oldTextPoint.x + oldWidth / 2 - newWidth / 2, oldTextPoint.y));

                    Point oldButtonPoint = button.getPoint();
                    button.setPoint(new Point((int) (oldButtonPoint.x - oldWidth / 2 + newWidth / 2), oldButtonPoint.y));
                    getRidOfTextChangeButtons();
                } else if (button instanceof StartButton) {
                    ((StartButton) button).start();
                    buttons.remove(button);
                    gameObjects.remove(button);
                    getRidOfTextChangeButtons();
                    for (Button temp : buttons) {
                        if(temp instanceof TextGuessedButton){
                            gameObjects.add(temp);
                        }
                    }
                } else if (button instanceof TextGuessedButton){
                    ((TextGuessedButton) button).guessed();
                    gameObjects.remove(button);
                    buttons.remove(button);
                }
                break;
            }
        }
    }
}
