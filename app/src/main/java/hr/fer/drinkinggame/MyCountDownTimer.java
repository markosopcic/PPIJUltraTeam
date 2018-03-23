package hr.fer.drinkinggame;

import android.content.res.AssetManager;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niko on 23.3.2018..
 */

public class MyCountDownTimer extends CountDownTimer {
    List<GameObject> gameObjects=new ArrayList<>();
    AssetManager manager;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval, List<GameObject> gameObjects, AssetManager manager ) {
        super(millisInFuture, countDownInterval);
        this.gameObjects = gameObjects;
        this.manager=manager;
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }
}
