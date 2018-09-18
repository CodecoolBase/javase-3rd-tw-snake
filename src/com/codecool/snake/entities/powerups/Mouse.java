package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;

import java.util.Random;



public class Mouse extends GameEntity implements Animatable, Interactable {


    private Point2D heading;
    private static final int life = 1;

    public Mouse (Pane pane) {
        super(pane);
        setImage(Globals.mouse);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        int speed = 1;
        double direction = -90;
        //setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }


    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        snakeHead.changeLives(life);
        destroy();

    }

    @Override
    public String getMessage() {
        return "Ate Mouse:)";
    }


    @Override
    public void spawn() {
        new Mouse(pane);
    }
}
