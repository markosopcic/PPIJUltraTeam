package hr.fer.drinkinggame;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import hr.fer.drinkinggame.pantomime.PantomimeKeyWords;

/**
 * Created by roman on 12-May-18.
 */

public class PantomimeTest {
    @Test
    public void randomKeyWords(){
        List<String> keyWords = PantomimeKeyWords.getRandomKeyWords();
        HashMap<String, Integer> mapa = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            String temp = PantomimeKeyWords.getRandomKeyWord(keyWords);
            if(mapa.containsKey(temp))
                mapa.put(temp, mapa.get(temp)+1);
            else
                mapa.put(temp, 1);
        }
        System.out.print(mapa.toString());
        System.out.print(mapa.size());
    }
}
