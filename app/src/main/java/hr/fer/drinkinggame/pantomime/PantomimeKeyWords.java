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
    private static List<String> keyWords = new ArrayList<String>(){{
        add("Auto");
        add("Romobil");
        add("Isus");
        add("Policajac");
        add("Lopov");
        add("Macka");
        add("Ursa");
        add("Mobitel");
        add("Trump");
        add("Hitler");
        add("CR7");
        add("Kompjutor");
        add("Maslacak");
        add("Kava");
        add("Kip");
        add("Mac");
        add("Puska");
        add("Stolica");
        add("Ves masina");
        add("Kad jaganjci utihnu");
        add("Stephen Hawking");
        add("Romano Barilar");
        add("Lezeci policajac");
        add("Lopta");
        add("Piva");
        add("Pelin");
        add("Freddy Mercury");
        add("Ceca");
        add("Tuđman");
        add("7 patuljaka");
        add("Glad");
        add("Frizider");
        add("Kruh");
        add("Slapa");
        add("Dota caca");
    }};

    public PantomimeKeyWords(){

    }

    public static List<String> getRandomKeyWords(){
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

    public static String getRandomKeyWord(List<String> keyWords){
        String temp;
        do {
            Random rand = new Random();
            temp = PantomimeKeyWords.keyWords.get(rand.nextInt(PantomimeKeyWords.keyWords.size()));
        } while(keyWords.contains(temp));
        return temp;
    }
}