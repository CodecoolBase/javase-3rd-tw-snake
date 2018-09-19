package com.codecool.snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Controls {

    public Controls(Game pane, int posX, int posY) {

        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(posX);
        restartButton.setLayoutY(posY);
        pane.getChildren().add(restartButton);

        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(posX + 70);
        pauseButton.setLayoutY(posY);
        pane.getChildren().add(pauseButton);

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("reset pressed");
                pane.restart();
            }
        });

        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("pause pressed");
                pane.pause();
            }
        });
    }
}
