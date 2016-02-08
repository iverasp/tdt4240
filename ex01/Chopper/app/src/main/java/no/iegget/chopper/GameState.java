package no.iegget.chopper;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

/**
 * Created by iver on 08/02/16.
 */
public class GameState extends State implements TouchListener {

    private Chopper mChopper1;
    private ArrayList<Chopper> choppers;

    public GameState(Resources resources) {
        choppers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            choppers.add(new Chopper(
                    BitmapFactory.decodeResource(resources, R.drawable.choppersprite),
                    ThreadLocalRandom.current().nextInt(0, 800 + 1 - 130),
                    ThreadLocalRandom.current().nextInt(0, 1024 + 1 - 52)
            ));
            choppers.get(i).setSpeed(
                    ThreadLocalRandom.current().nextInt(100, 500),
                    ThreadLocalRandom.current().nextInt(100, 500)
            );
        }
        mChopper1 = choppers.get(0);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(255, 0, 254));
        int screenWidth = canvas.getWidth();
        int screenHeight = canvas.getHeight();

        for (Chopper chopper : choppers) {

            for (Chopper otherChopper : choppers) {
                if (otherChopper == chopper) continue;
                if (otherChopper.getSpriteRect().intersect(chopper.getSpriteRect()) || chopper.getSpriteRect().intersect(otherChopper.getSpriteRect())) {
                    float chopperSpeedX = chopper.getSpeed().getX();
                    float chopperSpeedY = chopper.getSpeed().getY();
                    chopper.setSpeed(otherChopper.getSpeed().getX(), otherChopper.getSpeed().getY());
                    otherChopper.setSpeed(chopperSpeedX, chopperSpeedY);
                }
            }

            if (chopper.getPosX() <= 0 || chopper.getPosX() + chopper.getSpriteWidth() >= screenWidth) {
                chopper.setXSpeed(-chopper.getSpeed().getX());
            }
            if (chopper.getPosY() <= 0 || chopper.getPosY() + chopper.getSpriteHeight() >= screenHeight) {
                chopper.setYSpeed(-chopper.getSpeed().getY());
            }


            if (chopper.getSpeed().getX() < 0 && chopper.getDirection()) chopper.setDirection(false);
            if (chopper.getSpeed().getX() > 0 && !chopper.getDirection()) chopper.setDirection(true);

            chopper.draw(canvas);
        }

        Font font = new Font(0, 0, 0, 25, Typeface.DEFAULT, Typeface.BOLD);
        canvas.drawText("GET TO DA CHOPPA, which is at " + mChopper1.getPosX() + "," + mChopper1.getPosY(), 50, 50, font);

    }

    public void update(float delta) {
        for (Chopper chopper : choppers) {
            chopper.update(System.currentTimeMillis());
        }
    }

    @Override
    public boolean onTouchDown(MotionEvent event) {
        double xSpeed = -mChopper1.getPosX() + event.getX();
        double ySpeed = -mChopper1.getPosY() + event.getY();
        mChopper1.setXSpeed((float) xSpeed);
        mChopper1.setYSpeed((float) ySpeed);

        return super.onTouchDown(event);
    }

}
