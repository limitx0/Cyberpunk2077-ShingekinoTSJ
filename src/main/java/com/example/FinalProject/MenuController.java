package com.example.FinalProject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuController {
    @FXML private GridPane root;
    @FXML private Button Start;
    @FXML private Button Leave;


    

    public void onLeaveButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    public void onStartButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(Main.gameScene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);


//        Main.gameScene.getRoot().requestFocus();
////        Main.currentStage.setMaximized(true);
//        Main.currentStage.setScene(Main.gameScene);
//        Main.currentStage.setFullScreen(true);
    }
}