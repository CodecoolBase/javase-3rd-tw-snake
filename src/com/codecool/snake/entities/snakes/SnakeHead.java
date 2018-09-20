package com.codecool.snake.entities.snakes;

import com.codecool.snake.Curtain;
import com.codecool.snake.Game;
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
    public int lives;
    private int score;
    private int drunkTimeEnd = -1;
    private int superPowerEnd = -1;
    private double shootFrameEnd = -1;
    private double shootFrameDelay = 15;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        lives = Globals.lives;
        score = 0;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        addPart(8);
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

        // shooting
        if (Game.time < superPowerEnd && Game.time >= drunkTimeEnd) {
            setImage(Globals.laserSnakeHead);
            speed = 4;
            turnRate = 4;
            if (Globals.enemies.size() > 0) {
                if (Game.frame >= shootFrameEnd) {
                    shootFrameEnd = Game.frame + shootFrameDelay;
                    shoot(true, false);
                    shoot(true, true);
                }
            }
        } else {
            speed = 2;
            turnRate = 2;
            if (Globals.SpaceKeyDown && Game.time >= drunkTimeEnd) {
                setImage(Globals.laserSnakeHead);
                if (Game.frame >= shootFrameEnd) {
                    shootFrameEnd = Game.frame + shootFrameDelay;
                    shoot(false, false);
                }
            }
        }

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
        if (isOutOfBounds() || this.getLives() <= 0) {
            System.out.println("Game Over");
            Globals.isGamePaused = true;
            Globals.gameLoop.stop();
            Curtain.set(pane, Globals.gameOver);
        }
    }

    public void shoot(boolean superPower, boolean secondLaser){
        new Laser(pane, superPower, secondLaser);
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeLives(int diff) {
        if (this.getLives() < Globals.lives && diff>0 || diff<0) {
            this.setLives(this.getLives()+diff);
        }
        System.out.println("LIVES " + this.getLives());
    }

    public void changeScore(int diff) {
        this.setScore(getScore()+diff);
        System.out.println("SCORE  " + score );
    }

    public void intoxicateSnake(int duration){
        drunkTimeEnd = Game.time + duration;
    }

    public void superPower(int duration){
        drunkTimeEnd =- 5;
        superPowerEnd = Game.time + duration;
    }
}
