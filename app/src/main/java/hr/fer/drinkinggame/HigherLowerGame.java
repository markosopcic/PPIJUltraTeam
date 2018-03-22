package hr.fer.drinkinggame;

import android.content.res.AssetManager;
import android.graphics.Point;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marko on 22.3.2018..
 */

public class HigherLowerGame extends Game {
    private CardDeck deck;
    private List<Card> left;
    private Card right;


    public HigherLowerGame(AssetManager man, DisplayMetrics dm){
        deck=new CardDeck(man,dm);
        left=new ArrayList<>();
        int index;
        Random rand=new Random();
        Card c=deck.pullCardAtIndex(rand.nextInt(deck.sizeOfDeck()));
        c.changePoint(new Point(dm.widthPixels/2,dm.heightPixels/2));
        right=deck.pullCardAtIndex(rand.nextInt(deck.sizeOfDeck()));
        right.changePoint(new Point(0,0));
        this.gameObjects.addAll(left);
        this.gameObjects.add(right);
    }


}
