package hr.fer.drinkinggame.bombGame;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Vibrator;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.Game;
import hr.fer.drinkinggame.GameObject;
import hr.fer.drinkinggame.R;
import hr.fer.drinkinggame.pantomime.TextField;


/**
 * Created by Niko on 22.3.2018..
 */


public class BombGame extends Game {
    private TextField player;
    private TextField category;
    private Bomb bomb1;
    private Explosion expl;
    private Explosion armyBackground;
    private Button buttonNext;
    private DisplayMetrics dm;
    public boolean explosion;
    private ArrayList<String> nadimci;
    private MediaPlayer tick;
    private MediaPlayer ring;
    private  MyCountDownTimer myCountDownTimer1;


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
        categoires.add("Prijevozna sredstava");
        categoires.add("Gradovi u Dalmaciji");
        categoires.add("Vrste vina");
        categoires.add("Vrste alkoholnih pića");
        categoires.add("Marke tenisica");
        categoires.add("Zavodi na FER-u");
        categoires.add("Klubovi u NBA-u");
        categoires.add("Dobitnici Oscara");
        categoires.add("Države Europe");
        categoires.add("Programski jezici");
        categoires.add("Fakulteti u Zagrebu");
        categoires.add("Političke stranke");
        categoires.add("Žanrovi filmova");
        categoires.add("Realitiy showovi");
        categoires.add("Sisavci");
        categoires.add("Vrste voća");

        //zvukovi

        tick = MediaPlayer.create(context, R.raw.ticktick);
        ring= MediaPlayer.create(context,R.raw.explosionsound);

        //postavljanje pozadine

        Bitmap armyBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.army);
        armyBitmap=Bitmap.createScaledBitmap(armyBitmap,dm.widthPixels,dm.heightPixels,false);
        Point armyPoint=new Point(0,0);

        armyBackground = new Explosion(armyBitmap, armyPoint);

        this.gameObjects.add(armyBackground);

        //postavljanje kategorjie

        int index = ThreadLocalRandom.current().nextInt(0, categoires.size());
        String categoryString = categoires.get(index);

        TextPaint paint = new TextPaint();
        paint.setTextSize(90);
        paint.setColor(Color.RED);
        float textWidth = paint.measureText(categoryString);

        category = new TextField(categoryString, paint, new PointF(dm.widthPixels*5/10-textWidth/2,dm.heightPixels*1/10));

        this.gameObjects.add(category);

        //postavljajne igraca

        int radnomNumber2 = ThreadLocalRandom.current().nextInt(0, nadimci.size());

        textWidth = paint.measureText(nadimci.get(radnomNumber2));

        player = new TextField(nadimci.get(radnomNumber2), paint, new PointF(dm.widthPixels*5/10-textWidth/2,dm.heightPixels*5/20));

        this.gameObjects.add(player);

        //postavljanje bombe

        Bitmap bomb1Bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.bomba1);
        bomb1Bitmap=Bitmap.createScaledBitmap(bomb1Bitmap,dm.widthPixels*4/10,dm.heightPixels*25/100,false);
        Point bomba1Point=new Point(dm.widthPixels*3/10,dm.heightPixels*6/10 - bomb1Bitmap.getHeight());

        Bitmap bomb2Bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.bomba2);
        bomb2Bitmap=Bitmap.createScaledBitmap(bomb2Bitmap,dm.widthPixels*4/10,dm.heightPixels*25/100,false);
        Point bomba2Point=new Point(dm.widthPixels*3/10,dm.heightPixels*6/10 - bomb2Bitmap.getHeight());

        bomb1 = new Bomb(bomb1Bitmap,bomb2Bitmap, bomba1Point, this);

        this.gameObjects.add(bomb1);

        //inicijalizacija eksplozije

        Bitmap expBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.explode);
        expBitmap=Bitmap.createScaledBitmap(expBitmap,dm.widthPixels*7/10,dm.heightPixels*35/100,false);
        Point expPoint=new Point(dm.widthPixels*5/10 - expBitmap.getWidth()/2,dm.heightPixels*7/10 - expBitmap.getHeight());

        expl = new Explosion(expBitmap, expPoint);

        //postavljanje buttona

        Bitmap lowerBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.next);
        lowerBitmap=Bitmap.createScaledBitmap(lowerBitmap,dm.widthPixels*8/10,dm.heightPixels*2/10,false);
        Point lowerPoint=new Point(dm.widthPixels*9/10-lowerBitmap.getWidth(),dm.heightPixels*9/10-lowerBitmap.getHeight());
        buttonNext = new Button(lowerBitmap,lowerPoint);

        this.gameObjects.add(buttonNext);

        //pokretanje TIMERA

        int timeTimer = ThreadLocalRandom.current().nextInt(20, 35);

        myCountDownTimer1 = new MyCountDownTimer(timeTimer,this,tick, 1);
        gameObjects.add(myCountDownTimer1);
        myCountDownTimer1.start();

    }

    @Override
    public void handleTouch(MotionEvent event) {
        if(buttonNext.isButtonPressed(event) && explosion==false) {
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
            paint.setColor(Color.RED);
            float textWidth = paint.measureText(buduciIgrac);
           player = new TextField(buduciIgrac,paint, new PointF(dm.widthPixels*5/10-textWidth/2,dm.heightPixels*5/20));
           this.gameObjects.add(player);

        }
    }

    public void handleExplosion(){
        bomb1.setExplosion(true);
        gameObjects.remove(bomb1);
        gameObjects.add(expl);
        gameObjects.remove(myCountDownTimer1);
        MyCountDownTimer myCountDownTimer2 = new MyCountDownTimer(5,this,ring, 2);
        gameObjects.add(myCountDownTimer2);
        myCountDownTimer2.start();
    }

    public void endOfGame(){
        finished=true;
    }

    @Override
    public void pause(){
        tick.stop();
        ring.stop();
    }

}

