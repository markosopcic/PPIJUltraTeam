package hr.fer.drinkinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import hr.fer.drinkinggame.bombGame.BombGame;
import hr.fer.drinkinggame.higherlower.HigherLowerGame;
import hr.fer.drinkinggame.neverHaveIEver.NeverHaveIEver;
import hr.fer.drinkinggame.pantomime.Pantomime;

import static hr.fer.drinkinggame.MainThread.canvas;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int NUMBER_OF_GAMES=4;
    private MainThread thread;
    private Game currentGame;
    private Context context;
    public boolean paused=false;
    public int currentGameID=-1;
    public ArrayList<String> nadimci;
    private Pregame pregame;

    public GamePanel(Context context, ArrayList<String> nadimci) {
        super(context);
        this.context = context;
        this.nadimci = nadimci;
        getHolder().addCallback(this);


        setFocusable(true);
        setupNewPregame();
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MainThread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
            try {
                 thread.setRunning(false);
                 thread.join();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if( pregame!=null && !pregame.finished){
            pregame.handleTouch(event);
            return super.onTouchEvent(event);
        }
        if(currentGame!=null) {
            currentGame.handleTouch(event);
        }
        return super.onTouchEvent(event);
    }

    public void update() {
        if(!pregame.finished){
            pregame.update();
            return;
        }
       if(currentGame!=null) {
           if(!currentGame.finished)
                currentGame.update();
           else {
               setupNewPregame();
               currentGame=null;
           }
       }
       else{
            setupNewGame();
       }
     }


    @Override
    public void draw(Canvas canvas) {
        if(!pregame.finished){
            pregame.draw(canvas);
            return;
        }
        if(!paused) {
            super.draw(canvas);
            canvas.drawColor(Color.WHITE);
            if (currentGame != null) currentGame.draw(canvas);
        }
    }

    private void setupNewPregame(){
        Random rand=new Random();
        DisplayMetrics dm=context.getResources().getDisplayMetrics();




        //int newGameID=rand.nextInt(NUMBER_OF_GAMES);
        //while(newGameID==currentGameID) newGameID=rand.nextInt(NUMBER_OF_GAMES);
        int newGameID = 3;
        currentGameID=newGameID;
        switch(newGameID){
            case 0:{
                pregame=new Pregame("Bomba",context);
                pregame.setGameID(newGameID);

                break;
            }
            case 1:{
                pregame=new Pregame("Pantomime",context);
                pregame.setGameID(newGameID);
                break;
            }
            case 2:{
                pregame=new Pregame("Viša-Niža",context);
                pregame.setGameID(newGameID);
                break;
            }
            case 3:{
                pregame=new Pregame("Never have I ever",context);
                pregame.setGameID(newGameID);
                break;
            }
        }
    }

    private void setupNewGame(){
        switch(pregame.getGameID()){
            case 0:{
                currentGame=new BombGame(context,context.getResources().getDisplayMetrics(),nadimci);
                break;
            }
            case 1:{
                currentGame=new Pantomime(context,context.getResources().getDisplayMetrics(),thread,nadimci);
                break;
            }
            case 2:{
                currentGame=new HigherLowerGame(context,context.getResources().getDisplayMetrics(),nadimci);
                break;
            }
            case 3:{
                currentGame=new NeverHaveIEver(context,context.getResources().getDisplayMetrics());
                break;
            }
        }


    }


    public void pauseGame(){
        currentGame.pause();
    }
}
