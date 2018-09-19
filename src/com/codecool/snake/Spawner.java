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

    public Spawner(Pane pane, Class<? extends Object> entityClass, double time, int max) {


        spawnObject(pane, entityClass, time, max);
    }




    private void spawnObject(Pane pane, Class<? extends Object> entityClass, double time, int max) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {

            boolean snakeHeadPresent = false;
            int count =0;

            for (GameEntity gameObject : Globals.gameObjects) {
                if (gameObject.getClass() == entityClass) {
                    count++;
                }
            }


            for (GameEntity gameObject : Globals.gameObjects) {
                if (gameObject instanceof SnakeHead) {
                    snakeHeadPresent = true;
                    break;
                }
            }
            if (snakeHeadPresent && !Globals.isGamePaused && (count < max)) {
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
