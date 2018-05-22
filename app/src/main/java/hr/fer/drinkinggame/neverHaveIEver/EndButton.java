package hr.fer.drinkinggame.neverHaveIEver;

import android.graphics.Point;

import hr.fer.drinkinggame.higherlower.Button;
import java.io.InputStream;

import hr.fer.drinkinggame.GameObject;
import hr.fer.drinkinggame.pantomime.Clock;

/**
 * Created by roman on 22-May-18.
 */

public class EndButton extends Button implements GameObject {

    public EndButton(InputStream is, Point pos, int width, int height, NeverHaveIEver game) {
        super(is, pos, width, height);
    }
}
