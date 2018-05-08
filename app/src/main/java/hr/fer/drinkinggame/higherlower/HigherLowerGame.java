package hr.fer.drinkinggame.higherlower;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.List;
import java.util.Random;

import hr.fer.drinkinggame.Game;
import hr.fer.drinkinggame.R;

/**
 * Created by Marko on 22.3.2018..
 */

public class HigherLowerGame extends Game {
    private int numOfRounds;
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
    private boolean drink;
    private DrinkText drinkText;
    private CurrentPlayerText currText;

    private List<String> names;
    public HigherLowerGame(Context context, DisplayMetrics dm,List<String> nadimci){
        AssetManager man = context.getAssets();
        deck=new CardDeck(man,dm);
        Random rand=new Random();
        screenHeight=dm.heightPixels;
        names=nadimci;
        int y=(int)(dm.heightPixels*0.1);
        current=deck.pullCardAtIndex(rand.nextInt(deck.sizeOfDeck()));
        int x=dm.widthPixels/2-current.getCard().getWidth()/2;
        cardX=x;
        cardY=y;
        currText=new CurrentPlayerText(dm.widthPixels);
        currText.setCurrent(names.get(currentPlayerIndex));
        gameObjects.add(currText);
        numOfRounds=names.size()*(new Random().nextInt(2)+2);




        current.changePoint(new Point(x,y));
        this.gameObjects.add(current);
        Bitmap higherBitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.visa);
        higherBitmap=Bitmap.createScaledBitmap(higherBitmap,dm.widthPixels*3/10,dm.heightPixels*2/10,false);
        Point higherPoint=new Point(dm.widthPixels*1/10,dm.heightPixels*9/10-higherBitmap.getHeight());
        higher=new Button(higherBitmap,higherPoint);

        drinkText=new DrinkText(dm.widthPixels,dm.heightPixels*8/10-higherBitmap.getHeight());

        Bitmap lowerBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.niza);
        lowerBitmap=Bitmap.createScaledBitmap(lowerBitmap,dm.widthPixels*3/10,dm.heightPixels*2/10,false);
        Point lowerPoint=new Point(dm.widthPixels*9/10-lowerBitmap.getWidth(),dm.heightPixels*9/10-lowerBitmap.getHeight());
        lower=new Button(lowerBitmap,lowerPoint);
        this.gameObjects.add(higher);
        this.gameObjects.add(lower);
        speed=(0.5*screenHeight+current.getPoint().y)/500;
    }


    private long drinkstart=0;

    private int currentPlayerIndex;
    @Override
    public void handleTouch(MotionEvent event) {
        if(animating || startWait>0) return;
        if(higher.isButtonPressed(event)){
            newCard=deck.pullCardAtIndex(new Random().nextInt(deck.sizeOfDeck()));
            if(newCard.isHigher(current)){
                showCard();
            }
            else{
                    drinkstart=System.currentTimeMillis();
                    drinkText.setDrinking(names.get(currentPlayerIndex),"piješ");
                    gameObjects.add(drinkText);
                    drink=true;
                    showCard();
            }
            currText.setCurrent(names.get(currentPlayerIndex));
        }
        else if(lower.isButtonPressed(event)){
            newCard=deck.pullCardAtIndex(new Random().nextInt(deck.sizeOfDeck()));
            if(newCard.isLower(current)){
                showCard();
            }
            else{
                drinkstart=System.currentTimeMillis();
                drinkText.setDrinking(names.get(currentPlayerIndex),"piješ");
                gameObjects.add(drinkText);
                drink=true;
                showCard();
            }
            currText.setCurrent(names.get(currentPlayerIndex));
        }

    }

    private long startWait=0;
    @Override
    public void update() {
        super.update();
        if(startWait!=0){
            if(System.currentTimeMillis()-startWait>2000) finished=true;
            return;
        }
        if(drinkstart>0 && System.currentTimeMillis()-drinkstart>1000){
            drinkstart=0;
            gameObjects.remove(drinkText);
            if(!finished && numOfRounds<=0) {
                startWait=System.currentTimeMillis();
            }
        }
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
        }else{
            if(!finished && numOfRounds<=0) {
                startWait=System.currentTimeMillis();
            }
        }
    }

    public void showCard(){
        this.gameObjects.add(newCard);
        numOfRounds--;
        animating=true;
        newCard.changePoint(new Point(current.getPoint().x,-current.getCard().getHeight()));
        oldTime=System.currentTimeMillis();
        currentPlayerIndex=(currentPlayerIndex+1)%names.size();
    }
}
