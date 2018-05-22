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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddingDataActivity extends Activity {

    private LinearLayout parentLinearLayout;
    private ArrayList<String> categories;
    private int brojUcitanih;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adding_data);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        //za sad
        categories = new ArrayList<>();

        categories.add("BLA");
        categories.add("PRPR");

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
        categories = new ArrayList<>();
        int size = parentLinearLayout.getChildCount()-3;
        for(int i=0;i<brojUcitanih;++i) {
            LinearLayout v = (LinearLayout) parentLinearLayout.getChildAt(i+1);
            TextView textViewKategorija = (TextView) v.getChildAt(0);
            String tekstKategorija = textViewKategorija.getText().toString();
            categories.add(tekstKategorija);
        }



        for(int i=brojUcitanih;i<size;++i){
            LinearLayout v = (LinearLayout) parentLinearLayout.getChildAt(i+1);
            EditText editTekstKategorija = (EditText) v.getChildAt(0);
            String tekstKategorija = editTekstKategorija.getText().toString();
            if(tekstKategorija.isEmpty()){
                Toast.makeText(this, "Kategorija mora imati neki naziv", Toast.LENGTH_SHORT).show();
                editTekstKategorija.requestFocus();
                return;
            }
            categories.add(tekstKategorija);
        }



    }


    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        brojUcitanih--;
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
