package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.Beer;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.powerups.Mouse;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {
    public static int frame = 0;
    public static int time = 0;

    public Game() {
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new Mouse(this);
        new Mouse(this);
        new Mouse(this);
        new Mouse(this);
        new Mouse(this);
        new Mouse(this);
        new Mouse(this);


//        new Eagle (this);
//        new Eagle (this);
//        new Eagle (this);
//        new Eagle (this);
//        new Eagle (this);
//        new Eagle (this);


        new Beer(this);
        new Beer(this);
        new Beer(this);
        new Beer(this);
        new Beer(this);

    }

    public void start() {
        Scene scene = getScene();
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
}
