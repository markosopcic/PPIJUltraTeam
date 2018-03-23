package hr.fer.drinkinggame;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marko on 22.3.2018..
 */

public class CardDeck {

   private List<Card> cards;

    public CardDeck(AssetManager manager, DisplayMetrics dm){
        String[] c=null;
        try {
             c = manager.list("cards");
        }catch(Exception e){

        }
        cards=new ArrayList<Card>();
        int cardHeight,cardWidth;
        cardHeight=(int)(dm.heightPixels*0.4);
        cardWidth=(int)(dm.widthPixels*0.4);
        for(String s:c){
            Bitmap d=null;
            try {
                 d = BitmapFactory.decodeStream(manager.open("cards/"+s));
                 d=Bitmap.createScaledBitmap(d,cardWidth,cardHeight,false);
            }catch(Exception e){}
          String[] spl=s.split("/");

          Card card=new Card(d,spl[spl.length-1].split("\\.")[0],null);
          cards.add(card);
        }
    }

    public Card pullCardAtIndex(int index){
        if(index>=0 && index<cards.size()){
            Card c=cards.get(index);
            cards.remove(index);
            return c;

        }
        return null;
    }

    public int sizeOfDeck(){
        return cards.size();
    }
}
