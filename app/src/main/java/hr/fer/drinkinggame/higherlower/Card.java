package hr.fer.drinkinggame.higherlower;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import hr.fer.drinkinggame.GameObject;

/**
 * Created by Marko on 22.3.2018..
 */

public class Card implements GameObject {
    private Bitmap card;
    private String name;
    private Point point;

    public Card(Bitmap b,String n,Point p){
        card=b;
        name=n;
        point=p;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(card,point.x,point.y,new Paint());
    }

    @Override
    public void update() {

    }

    public void changePoint(Point newPoint){
        point=newPoint;
    }

    public Bitmap getCard(){
        return card;
    }

    public boolean isHigher(Card c){
        String s1=this.name.split("_")[0];
        String s2=c.name.split("_")[0];
        if(CardDeck.strengths.indexOf(s1)>CardDeck.strengths.indexOf(s2)){
            return true;
        }
        return false;
    }

    public boolean isLower(Card c){
        String s1=this.name.split("_")[0];
        String s2=c.name.split("_")[0];
        if(CardDeck.strengths.indexOf(s1)<CardDeck.strengths.indexOf(s2)){
            return true;
        }
        return false;
    }

    public Point getPoint(){
        return point;
    }
}
