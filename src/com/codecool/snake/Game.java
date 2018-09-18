package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.Beer;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.powerups.Mouse;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import static com.codecool.snake.Globals.eagle;
import static com.codecool.snake.Globals.getGameObjects;

public class Game extends Pane {
    public static int frame = 0;
    public static int time = 0;



    public Game() {

        SnakeHead head = new SnakeHead(this, 500, 500);

        new Spawner(new SimpleEnemy(this), 2.0);

        new Spawner(new Mouse(this), 2.5);

        new Beer(this);

        new SimplePowerup(this);

//        new Spawner(new Eagle(this), 0.5);

    }

    public void start() {
        System.out.println(Globals.getGameObjects().toString());
        Scene scene = getScene();
        setBackground();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();

    }

    public void setBackground() {
        setBackground(new Background(new BackgroundImage(Globals.grass,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
