package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    public int lives = 0;
    private int score = 0;
    private int drunkTimeEnd = -1;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        addPart(8);
        Globals.addGameObject(this);
    }

    public void step() {
        double dir = getRotate();

        // make snake squint when drunk
        setImage(Game.time < drunkTimeEnd ? Globals.drunkSnakeHead : Globals.snakeHead);

        // set turnRate with a correction on intoxication
        float actualTurnRate = Game.time < drunkTimeEnd ? -turnRate : turnRate;

        if (Globals.leftKeyDown) {
            dir = dir - actualTurnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + actualTurnRate;
        }

        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void changeLives(int diff) {
        if (lives < 3) {
            lives += diff;
        }
        System.out.println("LIVES " + lives);
    }

    public void changeScore(int diff) {
        score += diff;
        System.out.println("SCORE  " + score );
    }

    public void intoxicateSnake(int duration){
        drunkTimeEnd = Game.time + duration;
    }
}
