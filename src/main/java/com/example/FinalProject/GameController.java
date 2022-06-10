package com.example.FinalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController {
    @FXML private AnchorPane root;
    @FXML private Button Pause;

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
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(Main.pauseScene);
        stage.setFullScreen(true);

//        Main.pauseScene.getRoot().requestFocus();
////        Main.currentStage.setMaximized(true);
//        Main.currentStage.setScene(Main.pauseScene);
//        Main.currentStage.setFullScreen(true);
    }
    public void onKeyPressed(KeyEvent key) {
        System.out.println(key.getCode());
        if (key.getCode() == KeyCode.UP) {

        }
    }


}
