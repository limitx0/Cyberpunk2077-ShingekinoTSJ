package Controller;

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
        stage.setScene(Main.Main.gameScene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);


//        Main.Main.gameScene.getRoot().requestFocus();
////        Main.Main.currentStage.setMaximized(true);
//        Main.Main.currentStage.setScene(Main.Main.gameScene);
//        Main.Main.currentStage.setFullScreen(true);
    }
}