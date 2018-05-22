package hr.fer.drinkinggame.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "version")
public class Version {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String value;

    public Version(String value){
        this.value=value;
    }
}
