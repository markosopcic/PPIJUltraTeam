package hr.fer.drinkinggame.bombGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import hr.fer.drinkinggame.Game;
import hr.fer.drinkinggame.GameActivity;
import hr.fer.drinkinggame.GameObject;

public class Bomb implements GameObject {

    private Bitmap bomb1Bitmap;
    private Bitmap bomb2Bitmap;
    private Point point;
    private BombGame game;
    private boolean explosion=false;
    private boolean par=false;
    private Bitmap bitmap;


    private long startTime = System.nanoTime();
    private long lastChange = this.startTime;


    public Bomb(Bitmap bomba1Bitmap, Bitmap bomba2Bitmap, Point point, BombGame game){
        this.bitmap=bomba1Bitmap;
        this.bomb1Bitmap=bomba1Bitmap;
        this.bomb2Bitmap=bomba2Bitmap;
        this.point = point;
        this.game=game;

    }


    public void setExplosion(boolean explosion) {
        this.explosion = explosion;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,point.x,point.y,null);
    }

    @Override
    public void update() {
        if (explosion == false) {
            long currentTime = System.nanoTime();
            if ((currentTime - lastChange) >= 50000000) {
                lastChange = currentTime;
                //ovdje se događa čarolija za koju nisam samo ja zaslužan, već i moj dobri cimer
                if(par == false){
                    par = true;
                    this.bitmap = bomb1Bitmap;
                }else{
                    par = false;
                    this.bitmap = bomb2Bitmap;
                }
            }
        }
    }

}
