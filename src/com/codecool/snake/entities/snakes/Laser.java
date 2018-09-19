package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Laser extends GameEntity implements Animatable, Interactable {
    private Point2D heading;
    private SnakeHead snakeHead;
    private double dir;
    private int speed;

    public Laser(Pane pane) {
        super(pane);
        setSpeed(10);
        setImage(Globals.laser);
        pane.getChildren().add(this);
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof SnakeHead) {
                setSnakeHead((SnakeHead) gameObject);
            }
        }
        setDir(snakeHead.getRotate());
        setX(snakeHead.getX()+15);
        setY(snakeHead.getY()+8);
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }

    public int getSpeed() {
        return speed;
    }

    public double getDir() {
        return dir;
    }

    public void setDir(double dir) {
        this.dir = dir;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        // shoot lasers from snakeHead
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        setRotate(dir);
    }

    @Override
    public void apply(SnakeHead player) {
    }

    @Override
    public String getMessage() {
        return "Pew pew!";
    }

}

