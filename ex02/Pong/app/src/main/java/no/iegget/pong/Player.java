package no.iegget.pong;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by iver on 17/02/16.
 */

public interface Player {

    void draw(Canvas canvas);

    void update(float delta);

    int getPosX();

    void setPosX(int posX);

    int getPosY();

    void setPosY(int posY);

    int getScore();

    void setScore(int score);

    Rect getSpriteRect();

}
