package com.example.game1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

class Game extends SurfaceView implements SurfaceHolder.Callback {


    private final Player player;
    private final Joystic joystic;
    private final Enemy enemy;
    private GameLoop gameLoop;
    private Context context;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystic.isPressed((double)event.getX(),(double)event.getY())){
                    joystic.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystic.getIsPressed()){
                    joystic.setActuator((double)event.getX(),(double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystic.setIsPressed(false);
                joystic.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;

        gameLoop = new GameLoop(this,surfaceHolder);

        joystic = new Joystic(275,700,70,40);
        player = new Player(getContext(), joystic,2*500, 500,30);
        enemy = new Enemy(getContext(), player,500, 200,30);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        drawUPS(canvas);

        player.draw(canvas);
        joystic.draw(canvas);
        enemy.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " +averageUPS,100, 100, paint);
    }
    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " +averageFPS,100, 200, paint);
    }

    public void update() {
        player.update();
        joystic.update();
        enemy.update();
    }
}
