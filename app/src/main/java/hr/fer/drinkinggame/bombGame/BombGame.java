package hr.fer.drinkinggame.bombGame;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import hr.fer.drinkinggame.Button;
import hr.fer.drinkinggame.Game;
import hr.fer.drinkinggame.GameActivity;
import hr.fer.drinkinggame.GameObject;
import hr.fer.drinkinggame.R;
import hr.fer.drinkinggame.pantomime.TextField;

import static hr.fer.drinkinggame.MainThread.canvas;


/**
 * Created by Niko on 22.3.2018..
 */


public class BombGame extends Game {
    private List<String> categories;
    private TextField player;
    private TextField category;
    private Bomb bomb;
    private Bomb expl;
    private Button buttonNext;
    private DisplayMetrics dm;
    private boolean explosion;
    private ArrayList<String> nadimci;

    private Context context;

    public BombGame(final Context context, DisplayMetrics dm, ArrayList<String> nadimci){

        AssetManager manager = context.getAssets();

        this.dm = dm;
        this.nadimci = nadimci;

        explosion=false;

        List<String> categoires = new ArrayList<>();
        categoires.add("Marke automobila");
        categoires.add("Nogometni klubovi Hrvatske");
        categoires.add("Države Afrike");
        categoires.add("Države Azije");
        categoires.add("Odjevni predmeti");
        categoires.add("Marke mobitela");
        categoires.add("Predmeti na FER-u");
        categoires.add("Vrste prijevoznih sredstava");
        categoires.add("Gradovi u Dalmaciji");
        categoires.add("Vrste vina");
        categoires.add("Vrste alkoholnih pića");
        categoires.add("Marke tenisica");
        categoires.add("Zavodi na FER-u");
        categoires.add("Klubovi u NBA-u");
        categoires.add("Dobitnici Oscara");
        categoires.add("Države Europe");
        categoires.add("Fakulteti u Zagrebu");
        categoires.add("Političke stranke u Hrvatskoj");
        categoires.add("Žanrovi filmova");
        categoires.add("Realitiy showowi");

        int index = ThreadLocalRandom.current().nextInt(0, categoires.size());

        int radnomNumber = ThreadLocalRandom.current().nextInt(30, 41);
        final MediaPlayer tick = MediaPlayer.create(context, R.raw.ticktick);

        //Toast.makeText(context, categoires.get(index), Toast.LENGTH_LONG).show();

        String categoryString = categoires.get(index);

        //int textWidth = categoryString.measureText(temp);

        TextPaint paint = new TextPaint();
        paint.setTextSize(90);
        paint.setColor(Color.GREEN);
        float textWidth = paint.measureText(categoryString);

        category = new TextField(categoryString, paint, new PointF(dm.widthPixels*5/10-textWidth/2,dm.heightPixels*1/10));

        //ispis
        this.gameObjects.add(category);

        int radnomNumber2 = ThreadLocalRandom.current().nextInt(0, nadimci.size());

        textWidth = paint.measureText(nadimci.get(radnomNumber2));

        player = new TextField(nadimci.get(radnomNumber2), paint, new PointF(dm.widthPixels*5/10-textWidth/2,dm.heightPixels*3/10));

       this.gameObjects.add(player);


        Bitmap bombBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.bomba);
        bombBitmap=Bitmap.createScaledBitmap(bombBitmap,dm.widthPixels*4/10,dm.heightPixels*25/100,false);
        Point bombaPoint=new Point(dm.widthPixels*3/10,dm.heightPixels*6/10 - bombBitmap.getHeight());

        bomb = new Bomb(bombBitmap, bombaPoint);

        this.gameObjects.add(bomb);

        Bitmap expBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.expl);
        bombBitmap=Bitmap.createScaledBitmap(expBitmap,dm.widthPixels*5/10,dm.heightPixels*35/100,false);
        Point expPoint=new Point(dm.widthPixels*3/10,dm.heightPixels*6/10 - expBitmap.getHeight());

        expl = new Bomb(bombBitmap, expPoint);

        Bitmap lowerBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.niza);
        lowerBitmap=Bitmap.createScaledBitmap(lowerBitmap,dm.widthPixels*8/10,dm.heightPixels*2/10,false);
        Point lowerPoint=new Point(dm.widthPixels*9/10-lowerBitmap.getWidth(),dm.heightPixels*9/10-lowerBitmap.getHeight());
        buttonNext = new Button(lowerBitmap,lowerPoint);

        this.gameObjects.add(buttonNext);

        new MyCountDownTimer(radnomNumber*1000, 1000, this.gameObjects , manager) {
            //List<GameObject> gameObjects = null;

            public void setGameObjects(List<GameObject> gameObjects){
                this.gameObjects = gameObjects;
            }

            public void onTick(long millisUntilFinished) {
                tick.start();
            }

            public void onFinish() {
                explosion=true;
                gameObjects.remove(bomb);
                gameObjects.add(expl);
                final MediaPlayer ring= MediaPlayer.create(context,R.raw.explosionsound);
                tick.stop();
                ring.start();
                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(1000);

                new CountDownTimer(5000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        ring.stop();
                    }
                }.start();

            }
        }.start();


    }

    @Override
    public void handleTouch(MotionEvent event) {
        if(buttonNext.isButtonPressed(event) && explosion==false) {
            //samo za provjeru
           this.gameObjects.remove(player);
           String trenutniIgrac = player.getText();
           int radnomNumber2 = ThreadLocalRandom.current().nextInt(0, nadimci.size());
           String buduciIgrac = nadimci.get(radnomNumber2);
           while(trenutniIgrac.equals(buduciIgrac)){
               radnomNumber2 = ThreadLocalRandom.current().nextInt(0, nadimci.size());
               buduciIgrac = nadimci.get(radnomNumber2);
           }
            TextPaint paint = new TextPaint();
            paint.setTextSize(90);
            paint.setColor(Color.GREEN);
            float textWidth = paint.measureText(buduciIgrac);
           player = new TextField(buduciIgrac,paint, new PointF(dm.widthPixels*5/10-textWidth/2,dm.heightPixels*3/10));
           this.gameObjects.add(player);

        }
    }





}
