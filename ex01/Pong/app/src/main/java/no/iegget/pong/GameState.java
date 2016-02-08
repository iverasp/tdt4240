package no.iegget.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

/**
 * Created by iver on 07/02/16.
 */
public class GameState extends State implements TouchListener {

    private Paddle player1;
    private Paddle player2;
    private Ball ball;
    private int paddleLength = 200;
    private int paddleWidth = 20;
    private int x;
    private int y;
    private boolean gameOver;

    public GameState(int x, int y) {
        this.player1 = new Paddle(paddleWidth, paddleLength);
        this.player2 = new Paddle(paddleWidth, paddleLength);
        this.x = x;
        this.y = y;
        resetPlayers();
        this.ball = new Ball(x/2, y/2);
        ball.reset();
        gameOver = false;
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(0,0,0));
        int screenWidth = canvas.getWidth();
        int screenHeight = canvas.getHeight();
        Font font = new Font(255, 255, 255, 80, Typeface.DEFAULT, Typeface.BOLD);

        if (player1.getScore() == 21 || player2.getScore() == 21) gameOver = true;

        if (!gameOver) {

            ball.draw(canvas);
            player1.draw(canvas);
            player2.draw(canvas);

            // ball hits paddle
            if (ball.getSpriteRect().intersect(player1.getSpriteRect()) || ball.getSpriteRect().intersect(player2.getSpriteRect())) {
                ball.setYSpeed(ball.getSpeed().getY());
                ball.setXSpeed(-ball.getSpeed().getX());
            }

            // ball goes past paddle
            if (ball.getX() <= 0) {
                player1.setScore(player1.getScore() + 1);
                resetPlayers();
                ball.reset();
            } else if (ball.getX() + ball.getSize() >= x) {
                player2.setScore(player2.getScore() + 1);
                resetPlayers();
                ball.reset();
            }

            // ball hits top or bottom
            if (ball.getY() <= 0 || ball.getY() + ball.getSize() >= screenHeight)
                ball.setYSpeed(-ball.getSpeed().getY());

            // print score
            canvas.drawText(player1.getScore() + "", x / 2 + 140, 100, font);
            canvas.drawText(player2.getScore() + "", x / 2 - 160, 100, font);
        } else {
                String winner = player1.getScore() == 21 ? "player 1" : "player 2";
                canvas.drawText("WINNER: " + winner, 100, 100, font);
        }
    }

    public void update(float delta) {
        ball.update(delta);
        player1.update(delta);
        player2.update(delta);
    }

    private void resetPlayers() {
        this.player1.setPosition(0, y/2 - paddleLength/2);
        this.player2.setPosition(x - paddleWidth, y/2 - paddleLength/2);
        this.player1.setPosX(0);
        this.player1.setPosY(y / 2 - paddleLength / 2);
        this.player2.setPosX(x - paddleWidth);
        this.player2.setPosY(y / 2 - paddleLength / 2);
    }

    public boolean onTouchDown(MotionEvent event) {
        int eventY = (int) event.getY();
        if (eventY > y - paddleLength) eventY = y - paddleLength;
        if (event.getX() < x/2) {
            player1.setPosY(eventY);
        } else {
            player2.setPosY(eventY);
        }
        return super.onTouchDown(event);
    }

}
