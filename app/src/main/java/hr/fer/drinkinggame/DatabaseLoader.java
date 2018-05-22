package hr.fer.drinkinggame;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import hr.fer.drinkinggame.database.BombCategories;
import hr.fer.drinkinggame.database.DrinkingGameDatabase;
import hr.fer.drinkinggame.database.NeverHaveIEverWords;
import hr.fer.drinkinggame.database.PantomimeWords;
import hr.fer.drinkinggame.database.Version;
import hr.fer.drinkinggame.menus.MainMenuActivity;

public class DatabaseLoader {

    public static String BOMB_FILE_NAME="bomb_categories.txt";

    public static String NEVER_EVER_FILE="never_ever.txt";

    public static String PANTOMIME_FILE="pantomime.txt";

    public static String VERSION_FILE="version.txt";

    public static void load(Context context){
        DrinkingGameDatabase database= MainMenuActivity.database;
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(context.getAssets().open(VERSION_FILE),"UTF-8"));
            String version=reader.readLine();
            String dbVersion=database.databaseDao().getVersion();
            if(dbVersion==null || version.equals(dbVersion)==false){
                database.clearAllTables();
                Version vers=new Version(version);
                database.databaseDao().insertVersion(vers);
                loadInternal(context,database);
            }

        }catch(Exception e){
            Log.d("error",e.getMessage());}
    }

    private static void loadInternal(Context context,DrinkingGameDatabase database){
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(context.getAssets().open(BOMB_FILE_NAME),"UTF-8"));
            while(reader.ready()){
                String line=reader.readLine();
                BombCategories category=new BombCategories(line);
                database.databaseDao().insertBombCategory(category);
            }
            reader.close();
            reader=new BufferedReader(new InputStreamReader(context.getAssets().open(NEVER_EVER_FILE),"UTF-8"));
            while(reader.ready()){
                String line=reader.readLine();
                NeverHaveIEverWords word=new NeverHaveIEverWords(line);
                database.databaseDao().insertNeverHaveIEver(word);
            }
            reader.close();
            reader=new BufferedReader(new InputStreamReader(context.getAssets().open(PANTOMIME_FILE),"UTF-8"));
            while(reader.ready()){
                String line=reader.readLine();
                PantomimeWords word=new PantomimeWords(line);
                database.databaseDao().insertPantomimeWord(word);
            }
        }catch(Exception e){
            Log.d("error",e.getMessage());}

    }
}
