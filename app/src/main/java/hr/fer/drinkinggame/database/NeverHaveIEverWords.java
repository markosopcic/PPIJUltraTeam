package hr.fer.drinkinggame.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "never_have_i_ever")
public class NeverHaveIEverWords {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String value;

    public NeverHaveIEverWords(String value){
        this.value=value;
    }
}
