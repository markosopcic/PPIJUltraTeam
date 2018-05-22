package hr.fer.drinkinggame.pantomime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import hr.fer.drinkinggame.menus.MainMenuActivity;

/**
 * Created by roman on 23-Mar-18.
 */

public class PantomimeKeyWords {
    private List<String> keyWords;

    public PantomimeKeyWords(){
        keyWords = MainMenuActivity.database.databaseDao().getPantomimeWords();
    }

    public List<String> getRandomKeyWords(){
        Random rand  = new Random();
        List<String> rici = new ArrayList<>();
        Set<Integer> randomSet = new HashSet<>();
        while(randomSet.size()!=4){
            randomSet.add(rand.nextInt(keyWords.size()));
        }
        for (Integer temp : randomSet){
            rici.add(keyWords.get(temp));
        }
        return rici;
    }

    public String getRandomKeyWord(List<String> keyWords){
        String temp;
        do {
            Random rand = new Random();
            temp = keyWords.get(rand.nextInt(keyWords.size()));
        } while(keyWords.contains(temp));
        return temp;
    }
}