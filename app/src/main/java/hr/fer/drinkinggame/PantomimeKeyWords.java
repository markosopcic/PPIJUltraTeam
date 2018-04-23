package hr.fer.drinkinggame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by roman on 23-Mar-18.
 */

public class PantomimeKeyWords {
    private List<String> keyWords;

    public PantomimeKeyWords(){
        this.keyWords = new ArrayList<>();
        this.keyWords.add("Komunizam");
        this.keyWords.add("Macka");
        this.keyWords.add("Autobot");
        this.keyWords.add("Yolo");
        this.keyWords.add("Kalendar");
    }

    public List<String> getRandomKeyWords(){
        Random rand  = new Random();
        List<String> rici = new ArrayList<>();
        Set<Integer> randomSet = new HashSet<>();
        while(randomSet.size()!=4){
            randomSet.add(rand.nextInt(keyWords.size()-1));
        }
        for (Integer temp : randomSet){
            rici.add(keyWords.get(temp));
        }
        return rici;
    }
}