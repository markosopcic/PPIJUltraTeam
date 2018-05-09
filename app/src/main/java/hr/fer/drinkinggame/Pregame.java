package hr.fer.drinkinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.MotionEvent;

import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.pantomime.TextField;


public class Pregame extends Game {

    private Button start;
    private String name;
    private Context context;
    private Button play;
    private TextField f;
    private int gameID;

    public Pregame(String name,Context context){
        this.name=name;
        this.context=context;
        Bitmap bmp= BitmapFactory.decodeResource(context.getResources(),R.drawable.igraj);
        bmp=Bitmap.createScaledBitmap(bmp, context.getResources().getDisplayMetrics().widthPixels/2,context.getResources().getDisplayMetrics().heightPixels*2/10,false);
        Point pnt=new Point();
        pnt.x=context.getResources().getDisplayMetrics().widthPixels/2-bmp.getWidth()/2;
        pnt.y=context.getResources().getDisplayMetrics().heightPixels*9/10-bmp.getHeight();
        play=new Button(bmp,pnt);
        gameObjects.add(play);
        TextPaint paint=new TextPaint();
        paint.setTextSize(40*context.getResources().getDisplayMetrics().scaledDensity);
        PointF pf=new PointF();
        Rect rect=new Rect();
        paint.getTextBounds(name,0,name.length(),rect);
        pf.x=context.getResources().getDisplayMetrics().widthPixels/2-rect.width()/2;
        pf.y=context.getResources().getDisplayMetrics().heightPixels*1/10+rect.height()/2;
        f=new TextField(name,paint,pf);
        gameObjects.add(f);
    }

    @Override
    public void handleTouch(MotionEvent event) {
        if(play.isButtonPressed(event)){
            finished=true;
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        super.draw(canvas);
    }

    public void setText(String text){
        f.setText(text);
    }

    public void setGameID(int id){
        this.gameID=id;
    }

    public int getGameID(){
        return gameID;
    }
}
