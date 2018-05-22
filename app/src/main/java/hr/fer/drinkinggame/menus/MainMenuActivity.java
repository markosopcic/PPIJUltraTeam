package hr.fer.drinkinggame.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import hr.fer.drinkinggame.AddingDataActivity;
import hr.fer.drinkinggame.AddingPlayerActivity;
import hr.fer.drinkinggame.GameActivity;
import hr.fer.drinkinggame.R;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button startLetterGameBtn = findViewById(R.id.pokreniIgru);
        startLetterGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startGameIntent = new Intent(MainMenuActivity.this,
                        AddingDataActivity.class);
                startActivity(startGameIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
