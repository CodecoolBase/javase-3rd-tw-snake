package com.codecool.snake;

import com.codecool.snake.entities.Interactable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Spawner {

    public Spawner(Interactable entity, double time){
        spawnObject(entity, time);

    }

    private void spawnObject(Interactable entity, double time) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {
            entity.spawn();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
