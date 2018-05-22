package hr.fer.drinkinggame.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import hr.fer.drinkinggame.pantomime.Pantomime;

@Entity(tableName = "pantomime_words")
public class PantomimeWords {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String value;

    public PantomimeWords(String value){
        this.value=value;
    }
}
