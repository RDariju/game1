package com.example.game1;

import android.graphics.Canvas;

public abstract  class GameObjects {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;

    public GameObjects(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    protected static double getDistanceBetweenObjects(GameObjects obj1, GameObjects obj2) {
        return Math.sqrt(
                Math.pow(obj2.getPositionX() - obj1.getPositionX(),2)+
                        Math.pow(obj2.getPositionY()-obj1.getPositionY(),2)
        );
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    protected double getPositionX(){return positionX;}
    protected double getPositionY(){return positionY;}
}
