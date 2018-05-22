package hr.fer.drinkinggame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.fer.drinkinggame.database.BombCategories;
import hr.fer.drinkinggame.menus.MainMenuActivity;

public class AddingDataBombGameActivity extends Activity {

    private LinearLayout parentLinearLayout;
    private List<String> categories;
    private int brojUcitanih;

    @Override
    protected void onResume() {
        super.onResume();
        categories = MainMenuActivity.database.databaseDao().getBombCategories();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adding_data_bomb_game);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        //za sad
        categories = MainMenuActivity.database.databaseDao().getBombCategories();

        brojUcitanih = categories.size();
        
        //prikaz kategorija
        for(int i=0,l=categories.size();i<l;++i){
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.field2, null);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
            LinearLayout linearLayout = (LinearLayout) parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 3);
            TextView kategorija = (TextView) linearLayout.getChildAt(0);
            kategorija.setText(categories.get(i));
        }
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
        LinearLayout linearLayout = (LinearLayout) parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 3);
        EditText editTekstNadimak = (EditText) linearLayout.getChildAt(0);
        editTekstNadimak.requestFocus();
    }

    public void onDone(View view) {
        int size = parentLinearLayout.getChildCount()-3;



        for(int i=brojUcitanih;i<size;++i){
            LinearLayout v = (LinearLayout) parentLinearLayout.getChildAt(i+1);
            EditText editTekstKategorija = (EditText) v.getChildAt(0);
            String tekstKategorija = editTekstKategorija.getText().toString();
            if(tekstKategorija.isEmpty()){
                Toast.makeText(this, "Kategorija mora imati neki naziv", Toast.LENGTH_SHORT).show();
                editTekstKategorija.requestFocus();
                return;
            }
            BombCategories category=new BombCategories(tekstKategorija);
            MainMenuActivity.database.databaseDao().insertBombCategory(category);
        }

        finish();



    }


    public void onDelete2(View v) {
        LinearLayout linearLayout = (LinearLayout) v.getParent();
        TextView textView = (TextView) linearLayout.getChildAt(0);
        String nazivKategorije = textView.getText().toString();
        parentLinearLayout.removeView((View) v.getParent());
        brojUcitanih--;
        MainMenuActivity.database.databaseDao().removeBombCategory(nazivKategorije);

    }

    public void onDelete(View v){
        parentLinearLayout.removeView((View) v.getParent());
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onDone(null);
    }
}
