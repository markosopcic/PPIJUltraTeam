package hr.fer.drinkinggame.menus;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.stetho.Stetho;

import hr.fer.drinkinggame.AddingDataActivity;
import hr.fer.drinkinggame.AddingPlayerActivity;
import hr.fer.drinkinggame.DatabaseLoader;
import hr.fer.drinkinggame.GameActivity;
import hr.fer.drinkinggame.R;
import hr.fer.drinkinggame.database.DrinkingGameDatabase;


public class MainMenuActivity extends Activity {

    public static DrinkingGameDatabase database=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(database==null){
            database= Room.databaseBuilder(this,DrinkingGameDatabase.class,"drinking_game_database").allowMainThreadQueries().build();
        }
        DatabaseLoader.load(this);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main_menu);
        Button startLetterGameBtn = findViewById(R.id.pokreniIgru);
        startLetterGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startGameIntent = new Intent(MainMenuActivity.this,
                        AddingPlayerActivity.class);
                startActivity(startGameIntent);

            }
        });
        Button settings=findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent set=new Intent(MainMenuActivity.this, AddingDataActivity.class);
                startActivity(set);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
