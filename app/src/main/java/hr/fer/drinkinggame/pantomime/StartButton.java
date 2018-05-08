package hr.fer.drinkinggame.pantomime;

import android.graphics.Point;

import java.io.InputStream;

import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 30-Apr-18.
 */

public class StartButton extends Button implements GameObject {
    private Clock clock;

    public StartButton(InputStream is, Point pos, int width, int height, Clock clock) {
        super(is, pos, width, height);
        this.clock = clock;
    }

    public void start(){
        clock.start();
    }
}
