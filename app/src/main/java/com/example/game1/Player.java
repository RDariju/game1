package com.example.game1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND=400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS;
    private final Joystic joystic;




    public Player(Context context, Joystic joystic, double positionX, double positionY, double radius){
        super(context,ContextCompat.getColor(context,R.color.player),positionX, positionY,radius);
        this.joystic = joystic;

    }

    public void update() {
        velocityX = joystic.getActuatorX()*MAX_SPEED;
        velocityY = joystic.getActuatorY()*MAX_SPEED;

        positionX += velocityX;
        positionY += velocityY;
    }

    //setPosition
}
