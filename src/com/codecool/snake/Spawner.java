package com.codecool.snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;


public class Spawner {

    public Spawner(Pane pane, Class entityClass, double time){
        spawnObject(pane, entityClass, time);
    }

    private void spawnObject(Pane pane, Class<? extends Object> entityClass, double time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {
            if (Game.time > 1){
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
