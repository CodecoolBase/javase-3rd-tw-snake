package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class Spawner {
    SnakeHead snakeHead;

    public Spawner(Pane pane, Class entityClass, double time) {
        spawnObject(pane, entityClass, time);
    }

    private void spawnObject(Pane pane, Class<?> entityClass, double time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {
            boolean snakeHeadPresent = false;
            for (GameEntity gameObject : Globals.gameObjects) {
                if (gameObject instanceof SnakeHead) {
                    snakeHeadPresent = true;
                    snakeHead = (SnakeHead) gameObject;
                    break;
                }
            }

            // find a spawn point far away from snake
            if (snakeHeadPresent && !Globals.isGamePaused) {
                Random rnd = new Random();
                double x, y, distance;
                do {
                    x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                    y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
                    distance = (Math.sqrt(Math.pow(snakeHead.getX()-x,2) + Math.pow(snakeHead.getY()-y,2)));
                } while (distance < 200);

                try {
                    Class[] arguments = new Class[]{Pane.class, Double.class, Double.class};
                    entityClass.getDeclaredConstructor(arguments).newInstance(pane, x, y);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
