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
    private boolean superPower;
    private double dir;
    private int speed;


    // superPower
    private GameEntity target;
    private GameEntity secondTarget;
    private double closestDistanceToTarget;
    private double secondClosestDistanceToTarget;
    private double currentDistanceToTarget;
    double lastDirection;


    public Laser(Pane pane, boolean superPower, boolean secondLaser) {
        super(pane);
        setSpeed(superPower? 20 : 10);
        setsuperPower(superPower);
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
        lastDirection = getDir();

        // superPower
        closestDistanceToTarget = Math.sqrt(Math.pow(Globals.WINDOW_HEIGHT,2) + Math.pow(Globals.WINDOW_WIDTH,2));
        secondClosestDistanceToTarget = closestDistanceToTarget;
        for (GameEntity enemy : Globals.enemies) {
            currentDistanceToTarget = Math.sqrt(Math.pow(enemy.getX()-getX(),2) + Math.pow(enemy.getY()-getY(),2));
            if (currentDistanceToTarget < closestDistanceToTarget) {
                secondClosestDistanceToTarget = closestDistanceToTarget;
                closestDistanceToTarget = currentDistanceToTarget;
                target = enemy;
            } else if (currentDistanceToTarget < secondClosestDistanceToTarget && currentDistanceToTarget > closestDistanceToTarget) {
                secondClosestDistanceToTarget = currentDistanceToTarget;
                secondTarget = enemy;
            }
        }
        target = secondLaser? secondTarget : target;
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }

    public boolean issuperPower() {
        return superPower;
    }

    public void setsuperPower(boolean superPower) {
        this.superPower = superPower;
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

        // shoot lasers from snakeHead at the target
        if (superPower && Globals.getEnemies().contains(target)) {
            setDir((Math.atan2(target.getY() - getY(), target.getX() - getX()) * 180 / Math.PI) + 90);
            lastDirection = getDir();
        } else {
            setDir(lastDirection);
        }
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
    }

    @Override
    public String getMessage() {
        return "Pew pew!";
    }

}

