package hr.fer.drinkinggame;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marko on 22.3.2018..
 */

public class HigherLowerGame extends Game {
    private int numOfRounds=5;
    private CardDeck deck;
    private Card current;
    private Card newCard;
    private Button higher;
    private Button lower;
    private int cardX;
    private int cardY;
    private boolean animating=false;
    private int screenHeight;
    private double speed;
    private long oldTime;
    public HigherLowerGame(Context context, DisplayMetrics dm){
        AssetManager man = context.getAssets();
        deck=new CardDeck(man,dm);
        Random rand=new Random();
        screenHeight=dm.heightPixels;
        int y=(int)(dm.heightPixels*0.1);
        current=deck.pullCardAtIndex(rand.nextInt(deck.sizeOfDeck()));
        int x=dm.widthPixels/2-current.getCard().getWidth()/2;
        cardX=x;
        cardY=y;
        current.changePoint(new Point(x,y));
        this.gameObjects.add(current);
        Bitmap higherBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.visa);
        higherBitmap=Bitmap.createScaledBitmap(higherBitmap,dm.widthPixels*3/10,dm.heightPixels*2/10,false);
        Point higherPoint=new Point(dm.widthPixels*1/10,dm.heightPixels*9/10-higherBitmap.getHeight());
        higher=new Button(higherBitmap,higherPoint);

        Bitmap lowerBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.niza);
        lowerBitmap=Bitmap.createScaledBitmap(lowerBitmap,dm.widthPixels*3/10,dm.heightPixels*2/10,false);
        Point lowerPoint=new Point(dm.widthPixels*9/10-lowerBitmap.getWidth(),dm.heightPixels*9/10-lowerBitmap.getHeight());
        lower=new Button(lowerBitmap,lowerPoint);
        this.gameObjects.add(higher);
        this.gameObjects.add(lower);
        speed=(0.5*screenHeight+current.getPoint().y)/500;
    }


    @Override
    public void handleTouch(MotionEvent event) {
        if(animating) return;
        if(higher.isButtonPressed(event)){
            newCard=deck.pullCardAtIndex(new Random().nextInt(deck.sizeOfDeck()));
            if(newCard.isHigher(current)){
                this.gameObjects.add(newCard);
                numOfRounds--;
                animating=true;
                newCard.changePoint(new Point(current.getPoint().x,-current.getCard().getHeight()));
                oldTime=System.currentTimeMillis();
            }
            else{

            }
        }
        else if(lower.isButtonPressed(event)){
            newCard=deck.pullCardAtIndex(new Random().nextInt(deck.sizeOfDeck()));
            if(newCard.isLower(current)){
                this.gameObjects.add(newCard);
                numOfRounds--;
                animating=true;
                newCard.changePoint(new Point(current.getPoint().x,-current.getCard().getHeight()));
                oldTime=System.currentTimeMillis();
            }
            else{

            }
        }

    }

    @Override
    public void update() {
        super.update();
        if(animating){
            newCard.changePoint(new Point(newCard.getPoint().x,(int)(newCard.getPoint().y+(System.currentTimeMillis()-oldTime)*speed)));
            if(newCard.getPoint().y>=screenHeight*0.1){
                newCard.changePoint(new Point(newCard.getPoint().x,(int)(screenHeight*0.1)));
                animating=false;
                this.gameObjects.remove(current);
                current=newCard;
            }
            else{
                oldTime=System.currentTimeMillis();
            }
        }
    }
}
