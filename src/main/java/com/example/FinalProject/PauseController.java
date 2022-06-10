package com.example.FinalProject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PauseController {

    @FXML private GridPane root;
    @FXML private Button Resume;
    @FXML private Button Menu;
    @FXML private Button Leave;


    public void onMenuButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(Main.menuScene);
        stage.setFullScreen(true);

//        Main.menuScene.getRoot().requestFocus();
////        Main.currentStage.setMaximized(true);
//        Main.currentStage.setScene(Main.menuScene);
//        Main.currentStage.setFullScreen(true);
    }

    public void onResumeButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(Main.gameScene);
        stage.setFullScreen(true);

//        Main.gameScene.getRoot().requestFocus();
////        Main.currentStage.setMaximized(true);
//        Main.currentStage.setScene(Main.gameScene);
//        Main.currentStage.setFullScreen(true);
    }

    public void onLeaveButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }
}
