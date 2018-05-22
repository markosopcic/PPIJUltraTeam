package hr.fer.drinkinggame.neverHaveIEver;

import java.util.List;
import java.util.Random;

import hr.fer.drinkinggame.menus.MainMenuActivity;

/**
 * Created by roman on 22-May-18.
 */

public class NeverHaveIEvers {

    List<String> neverHaveIEvers;

    public NeverHaveIEvers(){
        neverHaveIEvers = MainMenuActivity.database.databaseDao().getneverHaveIEverWords();
    }

    public String getRandomNeverHaveIEver(){
        Random rand = new Random();
        return neverHaveIEvers.get(rand.nextInt(neverHaveIEvers.size()));
    }
}
