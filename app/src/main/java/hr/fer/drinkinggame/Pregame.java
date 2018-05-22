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

import java.util.List;
import java.util.Random;

import hr.fer.drinkinggame.generalobjects.Picture;
import hr.fer.drinkinggame.higherlower.Button;
import hr.fer.drinkinggame.pantomime.TextField;


public class Pregame extends Game {

    private Button start;
    private String name;
    private Context context;
    private Button play;
    private TextField f;
    private int gameID;
    private boolean isViking=false;
    private long time;
    List<String> players;
    private int color=Color.RED;

    public Pregame(String name,Context context,List<String> names){
        time=System.currentTimeMillis();
        this.name=name;
        players=names;
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
        Random random=new Random();
        if(random.nextInt(100)<10){
            isViking=true;
            Bitmap viking=BitmapFactory.decodeResource(context.getResources(),R.drawable.viking);
            int width=context.getResources().getDisplayMetrics().widthPixels*5/10;
            int height=context.getResources().getDisplayMetrics().heightPixels*4/10;
            viking=Bitmap.createScaledBitmap(viking,width,height,false);
            Point point=new Point(context.getResources().getDisplayMetrics().widthPixels/2-viking.getWidth()/2,height*4/10);
            Picture pic=new Picture(viking,point);
            this.gameObjects.add(pic);
            TextPaint tpaint=new TextPaint();
            tpaint.setColor(Color.GREEN);
            tpaint.setTextSize(context.getResources().getDisplayMetrics().widthPixels/10);
            String myname=players.get(random.nextInt(players.size()));
            PointF pf2=new PointF(point.x+viking.getWidth()/2-tpaint.measureText(myname)/2,point.y+viking.getHeight()+context.getResources().getDisplayMetrics().heightPixels/10);

            TextField field=new TextField(myname,tpaint,pf2);
            this.gameObjects.add(field);

        }
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
        if(isViking  && System.currentTimeMillis()-time>100){
            if(color==Color.RED) {
                color = Color.BLUE;
            }
            else
            if(color==Color.BLUE) color=Color.RED;
            time=System.currentTimeMillis();
        }
        if(!isViking) color=Color.WHITE;
        canvas.drawColor(color);

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
