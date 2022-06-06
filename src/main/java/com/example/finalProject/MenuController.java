package com.example.finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuController {
    public GridPane root;
    public Button Start;
    public Button Leave;

    @FXML
    private Label welcomeText;

    @FXML
    

    public void onLeaveButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void onStartButtonClick(ActionEvent actionEvent) {
        Main.gameScene.getRoot().requestFocus();
//        Main.currentStage.setMaximized(true);
        Main.currentStage.setScene(Main.gameScene);
        Main.currentStage.setFullScreen(true);
    }
}