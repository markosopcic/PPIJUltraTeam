package hr.fer.drinkinggame.bombGame;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.text.TextPaint;

import java.util.ArrayList;
import java.util.List;

import hr.fer.drinkinggame.GameObject;
import hr.fer.drinkinggame.pantomime.Pantomime;
import hr.fer.drinkinggame.pantomime.TextField;

/**
 * Created by Niko on 23.3.2018..
 */

public class MyCountDownTimer implements GameObject {
    BombGame game;
    TextField counterText;
    int counter;
    boolean running;
    MediaPlayer sound;
    int number;

    long startTime;
    long lastChange;

    public MyCountDownTimer(int counter, BombGame game, MediaPlayer sound, int number) {
        this.game = game;
        this.counter = counter;
        this.running = false;
        this.sound = sound;
        this.number = number;
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.lastChange = this.startTime;
        this.running = true;
        sound.start();
    }

    public void finish() {
        this.running = false;
        sound.stop();
        if(number==1){
            game.explosion = true;
            game.handleExplosion();
        }
        if(number==2) {
            game.endOfGame();
        }

    }

    @Override
    public void draw(Canvas canvas) {

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