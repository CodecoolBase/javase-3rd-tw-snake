package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;


public class Spawner {

    public Spawner(Pane pane, Class entityClass, double time) {
        spawnObject(pane, entityClass, time);
    }

    private void spawnObject(Pane pane, Class<? extends Object> entityClass, double time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {
            boolean snakeHeadPresent = false;
            for (GameEntity gameObject : Globals.gameObjects) {
                if (gameObject instanceof SnakeHead) {
                    snakeHeadPresent = true;
                    break;
                }
            }
            if (snakeHeadPresent && !Globals.isGamePaused) {
                try {
                    Class[] arguments = new Class[]{Pane.class};
                    entityClass.getDeclaredConstructor(arguments).newInstance(pane);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
