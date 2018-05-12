package hr.fer.drinkinggame.pantomime;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.text.TextPaint;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 12-May-18.
 */

public class EndClock implements GameObject {
    Pantomime game;
    boolean running;

    long startTime;

    public EndClock(Pantomime game) {
        this.game = game;
        this.running = false;
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void finish() {
        this.running = false;
        game.finish();
    }

    @Override
    public void draw(Canvas canvas) {    }

    @Override
    public void update() {
        if (running) {
            long currentTime = System.nanoTime();
            if ((currentTime - startTime) >= 7000000000L) {
                finish();
            }
        }
    }
}
