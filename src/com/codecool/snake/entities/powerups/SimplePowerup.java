package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Random;

public class SimplePowerup extends GameEntity implements Interactable {

    private static final int bonus = 5;
    private int life = 1;

    public SimplePowerup(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        snakeHead.changeLives(life);
        Heart.switchOnBlackHeart();
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

}
