package hr.fer.drinkinggame.pantomime;

import android.graphics.Color;
import android.graphics.Point;

import java.io.InputStream;

import hr.fer.drinkinggame.Button;
import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 30-Apr-18.
 */

public class TextGuessedButton extends Button implements GameObject {
    TextField text;

    public TextGuessedButton(InputStream is, Point pos, int width, int height) {
        super(is, pos, width, height);
    }

    public TextGuessedButton(InputStream is, TextChangeButton button) {
        super(is, button.getPoint(), button.getWidth(), button.getHeight());
    }

    public void guessed(){
        text.setColor(Color.GREEN);
    }
}
