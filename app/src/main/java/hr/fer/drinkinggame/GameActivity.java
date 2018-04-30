package hr.fer.drinkinggame;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends Activity {

    public ArrayList<String> nadimci;

    private GamePanel view=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view!=null) return;
        view=new GamePanel(this);
        setContentView(view);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        nadimci = (ArrayList<String>) getIntent().getSerializableExtra("nadimci");
    }

    @Override
    protected void onPause(){
        super.onPause();
        view.paused=true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        view.paused=false;
    }

    @Override
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        setContentView(view);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
