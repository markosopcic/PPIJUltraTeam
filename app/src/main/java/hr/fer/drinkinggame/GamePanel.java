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
import hr.fer.drinkinggame.pantomime.Pantomime;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int NUMBER_OF_GAMES=3;
    private MainThread thread;
    private Game currentGame;
    private Context context;
    public boolean paused=false;
    public int currentGameID=-1;
    public ArrayList<String> nadimci;

    public GamePanel(Context context, ArrayList<String> nadimci) {
        super(context);
        this.context = context;
        this.nadimci = nadimci;

        getHolder().addCallback(this);


        setFocusable(true);
        setupNewGame();
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
        currentGame.handleTouch(event);
        return super.onTouchEvent(event);
    }

    public void update() {
       if(currentGame!=null) {
           if(!currentGame.finished)
                currentGame.update();
           else
               setupNewGame();
       }
     }


    @Override
    public void draw(Canvas canvas) {
        if(!paused) {
            super.draw(canvas);
            canvas.drawColor(Color.WHITE);
            if (currentGame != null) currentGame.draw(canvas);
        }
    }

    private void setupNewGame(){
        Random rand=new Random();
        DisplayMetrics dm=context.getResources().getDisplayMetrics();

        int newGameID=rand.nextInt(NUMBER_OF_GAMES);
        while(newGameID==currentGameID) newGameID=rand.nextInt(NUMBER_OF_GAMES);
        switch(newGameID){
            case 0:{
                currentGameID=0;
                currentGame=new BombGame(context,dm,nadimci);
                break;
            }
            case 1:{
                currentGameID=1;
                currentGame=new Pantomime(context,dm,thread,nadimci);
                break;
            }
            case 2:{
                currentGameID=2;
                currentGame=new HigherLowerGame(context,dm,nadimci);
                break;
            }
        }
    }
}
