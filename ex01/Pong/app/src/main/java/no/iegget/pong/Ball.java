package no.iegget.pong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.concurrent.ThreadLocalRandom;

import sheep.game.Sprite;

/**
 * Created by iver on 07/02/16.
 */
public class Ball extends Sprite {

    private Rect spriteRect;
    private int posX;
    private int posY;
    private int startX;
    private int startY;
    private int size = 30;
    private Bitmap sprite;

    public Ball(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.startX = x;
        this.startY = y;
        setPosition(x, y);
        spriteRect = new Rect(0, 0, size, size);
        sprite = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        sprite.eraseColor(Color.WHITE);
    }

    public void draw(Canvas canvas) {
        posX = (int) (getX() + getSpeed().getX() / 100);
        posY = (int) (getY() + getSpeed().getY() / 100);
        Rect newRect = new Rect(posX, posY, posX + size, posY + size);
        canvas.drawBitmap(sprite, spriteRect, newRect, null);
    }

    public void reset() {
        setPosition(startX, startY);
        int[] dir = new int[]{-1, 1};
        this.setSpeed(
                ThreadLocalRandom.current().nextInt(200, 1000) * dir[ThreadLocalRandom.current().nextInt(0, 2)],
                ThreadLocalRandom.current().nextInt(200, 1000) * dir[ThreadLocalRandom.current().nextInt(0, 2)]
        );
    }

    public Rect getSpriteRect() {
        return new Rect(posX, posY, posX + size, posY + size);
    }

    public int getSize() {
        return this.size;
    }
}
