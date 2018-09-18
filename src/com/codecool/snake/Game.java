package com.codecool.snake;

import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.powerups.Beer;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.powerups.Mouse;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import static com.codecool.snake.Globals.eagle;

public class Game extends Pane {
    public static int frame = 0;
    public static int time = 0;
    private SimpleEnemy Enemy;
    private Pane pane;

//    private final Object Eagle;

    public Game() {
        new SnakeHead(this, 500, 500);

        new Spawner( new SimpleEnemy(this), 2.0);

        new Spawner( new Mouse(this), 2.5);

        new Spawner( new Eagle(this), 3.5);


//        new Spawner( new SimplePowerup(this), 2);

//        new Spawner( new Beer(this), 2);


    }

    public void start() {
        System.out.println(Globals.getGameObjects());
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
