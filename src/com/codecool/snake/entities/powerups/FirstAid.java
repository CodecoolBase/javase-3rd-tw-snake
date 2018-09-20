package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Random;

public class FirstAid extends GameEntity implements Interactable {

    private static final int bonus = 5;
    private int life = 1;

    public FirstAid(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.firstAid);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        snakeHead.changeLives(life);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

}
