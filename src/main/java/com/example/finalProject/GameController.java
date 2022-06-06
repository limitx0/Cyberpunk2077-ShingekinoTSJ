package com.example.finalProject;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class GameController {
    public GridPane root;
    public Button Pause;

    public GameController() {
        Thread thread = new Thread(() -> {
            while (true) {


                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {

    }
    public void onKeyPressed(KeyEvent key) {
        System.out.println(key.getCode());
        if (key.getCode() == KeyCode.UP) {

        }
    }

}
