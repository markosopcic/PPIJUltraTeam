package hr.fer.drinkinggame.neverHaveIEver;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.drinkinggame.Game;
import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.pantomime.TextField;
import hr.fer.drinkinggame.generalobjects.Picture;

/**
 * Created by roman on 22-May-18.
 */

public class NeverHaveIEver extends Game {

    private List<Button> buttons;

    private AssetManager am;
    private DisplayMetrics dm;
    private Context context;

    public NeverHaveIEver(Context context, DisplayMetrics dm){
        buttons = new ArrayList<>();
        am = context.getAssets();
        this.dm = dm;
        this.context = context;

        Random rand = new Random();
        int coinFlip = rand.nextInt(2);
        String haveI = "";
        switch (coinFlip){
            case 0:
                haveI = "Pije tko jest!";
                break;
            case 1:
                haveI = "Pije tko nije!";
                break;
        }
        TextPaint paint = initializeTextPaint(dm.density, 22, Color.GREEN);
        TextField tko = new TextField(haveI, paint, new PointF(dm.widthPixels/2,dm.heightPixels/2));
        this.gameObjects.add(tko);
        EndButton krajicnik =  null;
        try{
            krajicnik = new EndButton(am.open("neverhaveiever/endbutt.jpg"), new Point(dm.widthPixels/4, dm.heightPixels - dm.heightPixels/4), dm.widthPixels/2, dm.heightPixels/6, this );
        }catch (Exception e){

        }
        this.gameObjects.add(krajicnik);
        buttons.add(krajicnik);
        Picture upitnici = null;
        try {
            upitnici = new Picture(am.open("neverhaveiever/upitnici.png"), new Point(0, 0), (int) dm.widthPixels, (int) (dm.heightPixels / 4));
        }catch (Exception e){

        }
        this.gameObjects.add(upitnici);
    }

    public void finish(){
        finished=true;
    }

    private TextPaint initializeTextPaint(float density, int size, int color){
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(size * density);
        textPaint.setColor(color);
        return textPaint;
    }

    @Override
    public void handleTouch(MotionEvent event) {
        for(Button button : this.buttons){
            if (button.isButtonPressed(event)) {
                if (button instanceof EndButton) {
                    finish();
                }
            }

    }
}}
