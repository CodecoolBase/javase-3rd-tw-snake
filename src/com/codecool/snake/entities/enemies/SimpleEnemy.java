package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

import static com.codecool.snake.Utils.getShootByLaser;

public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = -1;

    public SimpleEnemy(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        setX(x);
        setY(y);
        Random rnd = new Random();
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
        Globals.addEnemy(this);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        getShootByLaser(this);
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeLives(damage);
        destroy();
        Heart.switchOffRedHeart();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

}
