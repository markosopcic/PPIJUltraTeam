package hr.fer.drinkinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import hr.fer.drinkinggame.bombGame.BombGame;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Game currentGame;
    private Context context;
    public boolean paused=false;
    public ArrayList<String> nadimci;

    public GamePanel(Context context, ArrayList<String> nadimci) {
        super(context);
        this.context = context;
        this.nadimci = nadimci;

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);

        setFocusable(true);
        //currentGame=new HigherLowerGame(context,getResources().getDisplayMetrics(), nadimci);
        //currentGame=new Pantomime(getResources().getDisplayMetrics(), nadimci);
        currentGame=new BombGame(context,getResources().getDisplayMetrics(), nadimci);
        Log.d("wat","wut");
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
       if(currentGame!=null) currentGame.update();
     }


    @Override
    public void draw(Canvas canvas) {
        if(!paused) {
            super.draw(canvas);
            canvas.drawColor(Color.WHITE);
            if (currentGame != null) currentGame.draw(canvas);
        }


    }
}
