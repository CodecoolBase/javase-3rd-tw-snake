package com.codecool.snake.entities;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Pane;
import sun.nio.ch.sctp.SctpNet;

import java.awt.*;
import java.util.LinkedList;

import static com.codecool.snake.Globals.heartList;

public class Heart extends GameEntity  {

    private boolean alive;

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Heart(Pane pane, int x, int y) {
        super(pane);
        this.alive = true;
        setImage(Globals.heart);
        pane.getChildren().add(this);
        heartList.add(this);

        setX(x);
        setY(y);

    }

    private void switchHeartColor() {

        setImage(this.alive == true ? Globals.heart : Globals.blackHeart);

    }

    public static void switchOffRedHeart() {
        for (Heart heart : heartList) {
            if (heart.alive) {
                heart.alive = false;
                heart.switchHeartColor();
                break;
            }
        }
    }

    public static void switchOnBlackHeart() {
        for (int i=heartList.size()-1; i>=0; i--) {
            if (heartList.get(i).alive == false) {
                heartList.get(i).alive = true;
                heartList.get(i).switchHeartColor();
                break;
            }
        }
    }


}






