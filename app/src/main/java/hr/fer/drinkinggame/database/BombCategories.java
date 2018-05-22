package hr.fer.drinkinggame.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "bomb_categories")
public class BombCategories {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String value;

    public BombCategories(String value){
        this.value=value;
    }
}
