package hr.fer.drinkinggame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddingPlayerActivity extends Activity {

    private LinearLayout parentLinearLayout;


    private List<String> nadimci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adding_player);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        nadimci = new ArrayList<String>();

    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }


    public void onDone(View view) {
        int size = parentLinearLayout.getChildCount()-3;
        for(int i=0;i<size;++i){
            LinearLayout v = (LinearLayout) parentLinearLayout.getChildAt(i+1);
            EditText editTekstOdgovor = (EditText) v.getChildAt(0);
            String tekstOdgovor = editTekstOdgovor.getText().toString();
            String nadimak = tekstOdgovor;
            nadimci.add(nadimak);
        }

        finish();
        startActivity(new Intent(getApplicationContext(), GameActivity.class));
    }



}
