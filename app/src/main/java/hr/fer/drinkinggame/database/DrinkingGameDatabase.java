package hr.fer.drinkinggame.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {BombCategories.class,NeverHaveIEverWords.class,PantomimeWords.class,Version.class},version=1)
public abstract class DrinkingGameDatabase extends RoomDatabase {

public abstract DatabaseDao databaseDao();

}
