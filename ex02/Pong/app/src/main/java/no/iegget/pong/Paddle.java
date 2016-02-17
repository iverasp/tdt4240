package no.iegget.pong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import sheep.game.Sprite;

/**
 * Created by iver on 07/02/16.
 */
public class Paddle extends Sprite {

    private int height;
    private int width;
    private Rect spriteRect;
    private Bitmap sprite;
    private int posX;
    private int posY;
    private int score;

    public Paddle(int width, int height) {
        this.height = height;
        this.width = width;
        this.spriteRect = new Rect(0, 0, width, height);
        this.sprite = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.sprite.eraseColor(Color.WHITE);
        setShape(width, height);
        this.posX = (int) getX();
        this.posY = (int) getY();
        this.score = 0;
    }

    public void draw(Canvas canvas) {
        Rect newRect = new Rect(posX, posY, posX + width, posY + height);
        canvas.drawBitmap(sprite, spriteRect, newRect, null);
    }

    public void update(float delta) {

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Rect getSpriteRect() {
        return new Rect(posX, posY, posX + width, posY + height);
    }
}
