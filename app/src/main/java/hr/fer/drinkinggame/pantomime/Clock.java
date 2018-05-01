package hr.fer.drinkinggame.pantomime;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.CountDownTimer;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;

import hr.fer.drinkinggame.Game;
import hr.fer.drinkinggame.GameObject;

/**
 * Created by roman on 24-Apr-18.
 */

public class Clock implements GameObject {
    Pantomime game;
    TextField counterText;
    int counter;
    PointF point;
    boolean running;

    long startTime;
    long lastChange;

    // zajebana stvar
    public Clock(int counter, TextPaint paint, PointF point, Pantomime game) {
        this.game = game;
        this.counter = counter;
        this.point = point;
        this.counterText = new TextField(String.valueOf(this.counter), paint, this.point);
        this.running = false;
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.lastChange = this.startTime;
        this.running = true;
    }

    public void finish() {
        this.running = false;
    }

    public void setPoint(PointF point) {
        this.point = point;
    }

    @Override
    public void draw(Canvas canvas) {
        this.counterText.draw(canvas);
    }

    @Override
    public void update() {
        if (running) {
            long currentTime = System.nanoTime();
            if (counter == 0) {
                finish();
            }
            if ((currentTime - lastChange) >= 1000000000) {
                lastChange = currentTime;
                this.counterText.setText(String.valueOf(--this.counter));
            }
        }
    }
}
