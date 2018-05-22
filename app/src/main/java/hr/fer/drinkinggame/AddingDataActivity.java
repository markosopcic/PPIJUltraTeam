package hr.fer.drinkinggame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import hr.fer.drinkinggame.R;
import hr.fer.drinkinggame.menus.MainMenuActivity;

public class AddingDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adding_data);
    }


    public void bombGame(View view) {
        Intent startGameIntent = new Intent(AddingDataActivity.this,
                AddingDataBombGameActivity.class);
        startActivity(startGameIntent);
    }

    public void pantomime(View view) {
        Intent startGameIntent = new Intent(AddingDataActivity.this,
                AddingDataPantomimeActivity.class);
        startActivity(startGameIntent);
    }

    public void neverHaveIever(View view) {
        Intent startGameIntent = new Intent(AddingDataActivity.this,
                AddingDataNeverHaveIEverActivity.class);
        startActivity(startGameIntent);
    }

    public void info(View view) {
        Intent startGameIntent = new Intent(AddingDataActivity.this,
                InfoActivity.class);
        startActivity(startGameIntent);
    }
}
