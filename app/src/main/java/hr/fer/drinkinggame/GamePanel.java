package hr.fer.drinkinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Alen on 6.11.2017..
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);

        setFocusable(true);

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
        while(true) {
            try {
                 thread.setRunning(false);
                 thread.join();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    public void update() {

     }

     private void updateWordPresentation() {

     }

    @Override
    public void draw(Canvas canvas) {


        super.draw(canvas);
    }
}
