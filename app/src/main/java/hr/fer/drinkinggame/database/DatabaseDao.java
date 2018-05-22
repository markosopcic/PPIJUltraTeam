package hr.fer.drinkinggame.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface DatabaseDao {

    @Insert
    public void insertBombCategory(BombCategories category);

    @Insert
    public void insertNeverHaveIEver(NeverHaveIEverWords word);

    @Insert
    public void insertPantomimeWord(PantomimeWords word);

    @Query("delete from pantomime_words where value==(:value) ")
    public void removePantomimeWord(String value);

    @Query("delete from never_have_i_ever where value==(:value)")
    public void removeNeverHaveIEverWord(String value);

    @Query("delete from bomb_categories where value==(:value)")
    public void removeBombCategory(String value);

    @Query("select value from bomb_categories")
    public List<String> getBombCategories();

    @Query("select value from pantomime_words")
    public List<String> getPantomimeWords();

    @Query("select value from never_have_i_ever")
    public List<String> getneverHaveIEverWords();

    @Query("select value from version")
    public String getVersion();
}
