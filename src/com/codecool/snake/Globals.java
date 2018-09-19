package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import com.codecool.snake.entities.Heart;

import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static int lives = 3;

    public static Image snakeHead = new Image("snake_head1.png");
    public static Image drunkSnakeHead = new Image("snake_head2.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image beer = new Image("beer.png");
    public static Image mouse = new Image("m.png");
    public static Image eagle = new Image("eagle.png");
    public static Image grass = new Image("grass.jpg");
    public static Image heart = new Image("heart.png");
    public static Image blackHeart = new Image("blackHeart.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean isGamePaused = false;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static List<Heart> heartList;



    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        heartList = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
