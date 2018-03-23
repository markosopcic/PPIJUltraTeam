package hr.fer.drinkinggame;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Niko on 22.3.2018..
 */


public class BombGame extends Game {
    private List<String> categories;
    private Category category;

    private Context context;

    public BombGame(final Context context, DisplayMetrics dm){

        AssetManager manager = context.getAssets();

        List<String> categoires = new ArrayList<>();
        categoires.add("Auti");
        categoires.add("Države Europe");
        categoires.add("Fakulteti u Zagrebu");
        categoires.add("Političke stranke u Hrvatskoj");
        int index = ThreadLocalRandom.current().nextInt(0, categoires.size());

        int radnomNumber = ThreadLocalRandom.current().nextInt(8, 16);
        final MediaPlayer tick = MediaPlayer.create(context, R.raw.ticktick);

        //Toast.makeText(context, categoires.get(index), Toast.LENGTH_LONG).show();

        category = new Category(categoires.get(index), new Point(dm.widthPixels/2,dm.heightPixels/2));

        //ispis
        this.gameObjects.add(category);

        new MyCountDownTimer(radnomNumber*1000, 1000, gameObjects , manager) { // 5000 = 5 sec
            List<GameObject> gameObjects = null;

            public void setGameObjects(List<GameObject> gameObjects){
                this.gameObjects = gameObjects;
            }

            public void onTick(long millisUntilFinished) {
                tick.start();
            }

            public void onFinish() {
                final MediaPlayer ring= MediaPlayer.create(context,R.raw.explosionsound);
                tick.stop();
                ring.start();
                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(1000);

                new CountDownTimer(5000, 1000) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        ring.stop();
                    }
                }.start();

            }
        }.start();


    }


}
