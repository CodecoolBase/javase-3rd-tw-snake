package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

import static com.codecool.snake.Utils.getShootByLaser;


public class Eagle extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private SnakeHead snakeHead;
    private int damage;
    private int speed;


    public Eagle(Pane pane, Double x, Double y) {
        super(pane);
        setDamage(-1);
        setSpeed(1);
        setImage(Globals.eagle);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof SnakeHead) {
                setSnakeHead((SnakeHead) gameObject);
            }
        }
        Globals.addEnemy(this);
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        // make eagle follow the snake
        double dir = (Math.atan2(snakeHead.getY() - getY(), snakeHead.getX() - getX()) * 180 / Math.PI) + 90;
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        setRotate(dir);

        getShootByLaser(this);
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeLives(damage);
        destroy();

    }

    @Override
    public String getMessage() {
        return "Eaten by Eagle";
    }

}

