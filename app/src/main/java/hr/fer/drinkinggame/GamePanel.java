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
    private GamePhase phase;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);

        setFocusable(true);

        phase = GamePhase.PRESENTING_WORD;
    }

    private enum GamePhase {
        //faza u kojoj igra prikazuje sliku, slovka i ispisuje riječ
        PRESENTING_WORD,
        //faza u kojoj igrač piše (drag and dropanjem slova) riječ
        TYPING_WORD;
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
        if(phase == GamePhase.PRESENTING_WORD) {
            return true;
        }
        //TODO dodati imoplementaciju za TYPING_WORD fazu
        return super.onTouchEvent(event);
    }

    public void update() {
        if(phase == GamePhase.PRESENTING_WORD) {
            updateWordPresentation();
            return;
        }
        //TODO dodati implementaciju za TYPING_WORD fazu
     }

     private void updateWordPresentation() {
        //TODO dodati implementaciju
     }

    @Override
    public void draw(Canvas canvas) {
        //TODO dodati crtanje objekata zajedničkih objema fazama

        if(phase == GamePhase.PRESENTING_WORD) {
            //TODO dodati crtanje objekata karakterističnih za PRESENTING_WORD fazu
            return;
        }

        //TODO dodati crtanje objekata karakterističnih za TYPING_WORD fazu

        super.draw(canvas);
    }
}
