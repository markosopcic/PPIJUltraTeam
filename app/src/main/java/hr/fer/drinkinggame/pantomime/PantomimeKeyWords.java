package hr.fer.drinkinggame.pantomime;

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
        this.keyWords.add("Auto");
        this.keyWords.add("Romobil");
        this.keyWords.add("Isus");
        this.keyWords.add("Policajac");
        this.keyWords.add("Lopov");
        this.keyWords.add("Macka");
        this.keyWords.add("Ursa");
        this.keyWords.add("Mobitel");
        this.keyWords.add("Trump");
        this.keyWords.add("Hitler");
        this.keyWords.add("CR7");
        this.keyWords.add("Kompjutor");
        this.keyWords.add("Maslacak");
        this.keyWords.add("Kava");
        this.keyWords.add("Kip");
        this.keyWords.add("Mac");
        this.keyWords.add("Puska");
        this.keyWords.add("Stolica");
        this.keyWords.add("Masina za pranje rublja");
        this.keyWords.add("Kad jaganjci utihnu");
        this.keyWords.add("Stephen Hawking");
        this.keyWords.add("King Romano Barilar");
        this.keyWords.add("Lezeci policajac");
        this.keyWords.add("Lopta");
        this.keyWords.add("Piva");
        this.keyWords.add("Pelin");
        this.keyWords.add("Freddy Mercury");
        this.keyWords.add("Ceca");
        this.keyWords.add("Tuđman");
        this.keyWords.add("7 patuljaka");
        this.keyWords.add("Glad");
        this.keyWords.add("Frizider");
        this.keyWords.add("Kruh");
        this.keyWords.add("Slapa");
        this.keyWords.add("Dota caca");
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