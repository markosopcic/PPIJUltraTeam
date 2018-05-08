package hr.fer.drinkinggame;

import android.graphics.Canvas;
import android.os.Looper;
import android.view.SurfaceHolder;



public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMilis;
        long waitTime;
        long targetTime = 1000/MAX_FPS;

        while(this.running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                if(Looper.getMainLooper()==null)Looper.prepare();
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gamePanel.update();
                    gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas!=null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            timeMilis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMilis;
            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
