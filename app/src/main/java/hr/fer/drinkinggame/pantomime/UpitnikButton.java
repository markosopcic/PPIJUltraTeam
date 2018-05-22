package hr.fer.drinkinggame.pantomime;

import android.graphics.Point;

import java.io.InputStream;

import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 23-May-18.
 */

public class UpitnikButton extends Button implements GameObject {
    public UpitnikButton(InputStream is, Point pos, int width, int height) {
        super(is, pos, width, height);
    }
}
