package no.iegget.chopper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import sheep.game.Sprite;

/**
 * Created by iver on 08/02/16.
 */
public class Chopper extends Sprite {

    private Bitmap sprite;
    private int frameCounter = 0;
    private Rect spriteRect;
    private int posX;
    private int posY;
    private long timeCounter;
    private boolean direction; // true if going right
    private int spriteWidth;
    private int spriteHeight;

    public Chopper(Bitmap bitmap, int x, int y) {
        sprite = bitmap;
        spriteWidth = bitmap.getWidth() / 4;
        spriteHeight = bitmap.getHeight();
        spriteRect = new Rect(0, 0, bitmap.getWidth()/4, bitmap.getHeight());
        posX = x;
        posY = y;
        timeCounter = 0;
        flipSprite();
        direction = true;
    }

    public void update(long delta) {
        if (delta > timeCounter + 10) {
            timeCounter = delta;
            frameCounter++;
            if (frameCounter > 3) frameCounter = 0;
        }
        spriteRect.left = frameCounter * sprite.getWidth() / 4;
        spriteRect.right = spriteRect.left + sprite.getWidth() / 4;
    }

    public void draw(Canvas canvas) {
        setPosX((int) (posX + getSpeed().getX()/100));
        setPosY((int) (posY + getSpeed().getY()/100));
        Rect newRect = new Rect(posX, posY, posX + sprite.getWidth()/4, posY + sprite.getHeight());
        canvas.drawBitmap(sprite, spriteRect, newRect, null);
    }

    public void flipSprite() {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = sprite;
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        sprite = new BitmapDrawable(dst).getBitmap();
    }

    public void setDirection(boolean direction) {
        if (direction != this.direction) {
            flipSprite();
            this.direction = direction;
        }
    }

    public boolean getDirection() {
        return this.direction;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public Rect getSpriteRect() {
        return new Rect(posX, posY, posX + spriteWidth, posY + spriteHeight);
    }
}
