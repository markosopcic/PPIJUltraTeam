package hr.fer.drinkinggame.pantomime;

import android.graphics.Point;

import java.io.InputStream;

import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 29-Apr-18.
 */

public class TextChangeButton extends Button implements GameObject {
    Pantomime game;
    TextField text;
    int width;
    int height;

    public TextChangeButton(InputStream is, int width, int height, Point point, TextField text){
        super(is,  point, width, height);
        this.text = text;
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }
}
