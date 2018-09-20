package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.objects.Global;

public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int score;
    private int drunkTimeEnd = -1;
    private double shootFrameEnd = -1;
    private double shootFrameDelay = 15;
    double dir;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SnakeHead(Pane pane, double xc, double yc) {
        super(pane);
        setX(xc);
        setY(yc);
        tail = this;
        score = 0;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        addPart(Globals.snakeLength);
    }

    public void step() {
        dir = getRotate();

        // make snake squint when drunk
        setImage(Game.time < drunkTimeEnd ? Globals.drunkSnakeHead : Globals.snakeHead);

        // set turnRate with a correction on intoxication
        float actualTurnRate = Game.time < drunkTimeEnd ? -turnRate : turnRate;

        if (Globals.leftKeyDown) {
            dir = dir - actualTurnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + actualTurnRate;
        }

        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // shooting
        if (Globals.SpaceKeyDown && Game.time >= drunkTimeEnd){
            setImage(Globals.laserSnakeHead);
            if (Game.frame >= shootFrameEnd) {
                shootFrameEnd = Game.frame + shootFrameDelay;
                shoot();
            }
        }

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (Globals.lives <= 0) {
            System.out.println("Game Over");
            Globals.isGamePaused = true;
            Globals.gameLoop.stop();
        }

        if (isOutOfBounds()){
            changeLives(-1);
            Game.reSpawnSnake();}

    }

    public void shoot(){
        new Laser(pane);
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeLives(int diff) {
        if (Globals.lives  < Globals.MAX_LIVES && diff>0 || diff<0) {
            Globals.lives += diff;
        }
        System.out.println("LIVES " + Globals.lives);

        if (diff>0){
            Heart.switchOnBlackHeart();
        } else {
            Heart.switchOffRedHeart();
        }
    }

    public void changeScore(int diff) {
        this.setScore(getScore()+diff);
        System.out.println("SCORE  " + score );
    }

    public void intoxicateSnake(int duration){
        drunkTimeEnd = Game.time + duration;
    }

}
