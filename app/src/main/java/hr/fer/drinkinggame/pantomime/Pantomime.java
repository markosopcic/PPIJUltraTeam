package hr.fer.drinkinggame.pantomime;

import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.Game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.drinkinggame.GameObject;
import hr.fer.drinkinggame.MainThread;
import hr.fer.drinkinggame.generalobjects.Background;
import hr.fer.drinkinggame.generalobjects.Picture;

/**
 * Created by roman on 23-Mar-18.
 */

public class Pantomime extends Game {
    private List<String> keyWords;
    private ArrayList<String> nadimci;

    private List<Button> buttons;
    private List<Button> buttonsToAdd;

    PantomimeKeyWords pantomimeKeyWords;

    private AssetManager am;
    private DisplayMetrics dm;
    private Context context;



    private float drawingHeight;
    private int guessed;


    public Pantomime(Context context, DisplayMetrics dm, MainThread thread, ArrayList<String> nadimci) {
        this.pantomimeKeyWords = new PantomimeKeyWords();

        this.keyWords = new ArrayList<>();
        this.keyWords.addAll(pantomimeKeyWords.getRandomKeyWords());

        this.buttons = new ArrayList<>();
        this.buttonsToAdd = new ArrayList<>();

        this.nadimci=nadimci;

        am = context.getAssets();
        this.dm = dm;
        this.context = context;

        this.drawingHeight = 0;
        this.guessed = 0;

        setBackground();
        addPantomimeSign();
        addPlayers();
        Clock clock = initializeClock();
        addTextFieldsAndButtons();
        createStartButton(clock);
    }

    private void setBackground(){
        Background background = new Background(dm, Color.BLACK);
        this.gameObjects.add(background);
    }

    public void addPantomimeSign(){
        float x = dm.widthPixels;
        float y = dm.heightPixels;
        float margin = 5 * dm.density;
        Picture pantomime = null;
        // new Point((int)(x/4), (int)(0+ margin))
        try{
        pantomime = new Picture(am.open("pantomime/pantomime2.png"), new Point(0, (int)(margin)), (int)(x) ,(int)(66*dm.density));
        }
        catch (Exception e){

        }
        this.gameObjects.add(pantomime);
        drawingHeight += 66 * dm.density;
    }

    public void addPlayers(){
        Random rand = new Random();
        TextPaint textPaint = initializeTextPaint(dm.density, 25, Color.WHITE);
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        float margin = 5 * dm.density;
        String playerOne = "Objašnjava:" + nadimci.get(rand.nextInt(nadimci.size()));
        String playerTwo = "Pogađa:" + nadimci.get(1);
        TextField explainer = new TextField(playerOne, textPaint, new PointF(margin,drawingHeight + margin - fm.ascent + 40/3 * dm.density));
        // krenemo od drawingHeighta dodamo marginu razlike i pomaknemo za ascent
        // pomaknemo se gdje je prvi te otamo micemo za jedan desent marginu i ascent
        this.gameObjects.add(explainer);
    }

    private Clock initializeClock(){
        TextPaint textPaint = initializeTextPaint(dm.density, 40, Color.WHITE);
        Paint.FontMetrics fm = textPaint.getFontMetrics();

        float width = textPaint.measureText("60");
        float margin = 5 * dm.density;

        Clock clock = new Clock(60, textPaint, new PointF(dm.widthPixels - margin - width,drawingHeight - fm.ascent + margin), this);
        this.gameObjects.add(clock);
        return clock;
    }

    public void addTextFieldsAndButtons(){
        TextPaint textPaint = initializeTextPaint(dm.density, 30, Color.WHITE);
        Paint.FontMetrics fm = textPaint.getFontMetrics();

        float density = dm.density;
        float x = dm.widthPixels/2;
        float y = dm.heightPixels/2;
        //(x,y) na pola ekrana
        float descent = fm.descent;
        float ascent = -fm.ascent;
        float marginTaB = 5 * density; // top and bottom margin

//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        this.gameObjects.add(new Line(x,0, x,y*2, paint));
//        this.gameObjects.add(new Line(0, y, x*2,y, paint));

//        this.gameObjects.add(new TextField("Ovo je proba", textPaint, new PointF(x-textPaint.measureText("Ovo je proba")/2, y)));
//        this.gameObjects.add(new Line(x-textPaint.measureText("Ovo je proba")/2, y, x-textPaint.measureText("Ovo je proba")/2, y-ascent, paint));

        float drawingWidth = x;
        float drawingHeight = (float) (y - descent * 2 - marginTaB * 2 - ascent * 2 + marginTaB/2);
        float totalHeight = drawingHeight;
        // x na pola, y na vrhu gjde krece zapis prvog
        for (String temp : this.keyWords){
            textPaint = initializeTextPaint(dm.density, 30, Color.WHITE);
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
            button = new TextChangeButton(am.open("pantomime/change_word.png"), buttonWidth, buttonHeight, buttonPoint, text);}
            catch (Exception e){

            }
            this.buttonsToAdd.add(button);

            drawingHeight += marginTaB + descent;
        }
        this.drawingHeight = drawingHeight;
        UpitnikButton upitnik = null;
        try{
            upitnik= new UpitnikButton(am.open("pantomime/upitnik.jpg"), new Point(dm.widthPixels/20, (int)(totalHeight)), dm.widthPixels*18/20, (int)(drawingHeight-totalHeight) );
        }catch (Exception e){

        }
        this.buttons.add(upitnik);
        this.gameObjects.add(upitnik);
    }

    private TextPaint initializeTextPaint(float density, int size, int color){
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(size * density);
        textPaint.setColor(color);
        return textPaint;
    }

    private void createStartButton(Clock clock){
        float x = dm.widthPixels/2;
        float y = dm.heightPixels/2;
        float startButtonWidth = x;
        float startButtonHeight = ((y*2) - drawingHeight)/2;
        Point startButtonPoint = new Point((int)(x - startButtonWidth/2),(int) (drawingHeight + startButtonHeight/2));
        Button startButton = null;
        try{
            startButton = new StartButton(am.open("pantomime/start.png"),startButtonPoint, (int)startButtonWidth, (int)startButtonHeight, clock);}
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
                buttonsToAdd.add(new TextGuessedButton(am.open("pantomime/guessed_word.png"), ((TextChangeButton)temp)));
            }
            catch (Exception e){
            }
        }
    }

    public void endGuessing(){
        TextPaint textPaint = initializeTextPaint(dm.density, 30, Color.WHITE);
        float x = dm.widthPixels;
        float y = dm.heightPixels;
        String text1 = "Pogodili ste " + Integer.toString(guessed) + "/4.";
        String text2 = "Piješ " + Integer.toString(4-guessed) + ", dijeliš " + Integer.toString(guessed) + ".";
        gameObjects.add(new TextField(text1, textPaint, new PointF(x/4, drawingHeight+(y-drawingHeight)/4)));
        gameObjects.add(new TextField(text2, textPaint, new PointF(x/4, drawingHeight+(y-drawingHeight)/4 - textPaint.ascent() + textPaint.descent() + dm.density * 5)));
        getRidOfTextGuessedButtons();
        EndClock endClock = new EndClock(this);
        gameObjects.add(endClock);
        endClock.start();
    }

    public void finish(){
        finished=true;
    }

    private void getRidOfTextGuessedButtons(){
        List<Button> tempButtons = new ArrayList<>();
        for(Button temp : buttons){
            if (temp instanceof TextGuessedButton) {
                tempButtons.add(temp);
                ((TextGuessedButton) temp).text.setColor(Color.RED);
            }
        }
        for(Button temp : tempButtons){
            buttons.remove(temp);
            gameObjects.remove(temp);
        }
    }

    @Override
    public void handleTouch(MotionEvent event) {
        for(Button button : this.buttons){
            if (button.isButtonPressed(event)) {
                if (button instanceof TextChangeButton) {
//                    if(changedField)
//                        break;
//                    changedField = true;
                    String temp = pantomimeKeyWords.getRandomKeyWord(keyWords);
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
                    for (Button temp : buttonsToAdd) {
                        if(temp instanceof TextGuessedButton){
                            gameObjects.add(temp);
                            buttons.add(temp);
                        }
                    }
                } else if (button instanceof TextGuessedButton){
                    ((TextGuessedButton) button).guessed();
                    gameObjects.remove(button);
                    buttons.remove(button);
                    guessed++;
                    if (guessed == 4){
                        for (GameObject temp : this.gameObjects) {
                            if (temp instanceof Clock){
                                ((Clock) temp).finish();
                                break;
                            }
                        }
                    }
                } else if (button instanceof UpitnikButton){
                    gameObjects.remove(button);
                    buttons.remove(button);
                    List<Button> tempButtons = new ArrayList<>();
                    for (Button temp : buttonsToAdd){
                        if (temp instanceof TextChangeButton){
                            this.buttons.add(temp);
                            this.gameObjects.add(temp);
                            tempButtons.add(temp);
                        }
                    }
                    for (Button temp : tempButtons){
                        buttonsToAdd.remove(temp);
                    }

                }
                break;
            }
        }
    }
}
