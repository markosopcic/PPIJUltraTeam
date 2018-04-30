package hr.fer.drinkinggame.pantomime;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.io.InputStream;

import hr.fer.drinkinggame.Button;
import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 28-Apr-18.
 */

public class PantomimeButton implements GameObject {
    Button button;
    TextField field;
    Pantomime game;

    public PantomimeButton(AssetManager am, String image, TextField field, int width, int height, Point point){
        this.field = field;
        Bitmap buttonBitmap = null;
        try {
            buttonBitmap = BitmapFactory.decodeStream(am.open(image));
            buttonBitmap = Bitmap.createScaledBitmap(buttonBitmap, width, height, false);

        }
        catch (Exception e){
        }
        this.button = new Button(buttonBitmap, point);
    }

    public boolean isButtonPressed(MotionEvent event){
        return button.isButtonPressed(event);
    }

    public void doTheDeed(){

    }

    public void doTheDeed(Pantomime game){

    }

    public void setButton(){

    }

    @Override
    public void draw(Canvas canvas) {
        button.draw(canvas);
    }

    @Override
    public void update() {

    }
}
