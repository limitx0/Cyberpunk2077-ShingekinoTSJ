package Controller;


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
        stage.setScene(Main.Main.menuScene);
        stage.setFullScreen(true);

//        Main.Main.menuScene.getRoot().requestFocus();
////        Main.Main.currentStage.setMaximized(true);
//        Main.Main.currentStage.setScene(Main.Main.menuScene);
//        Main.Main.currentStage.setFullScreen(true);
    }

    public void onResumeButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(Main.Main.gameScene);
        stage.setFullScreen(true);

//        Main.Main.gameScene.getRoot().requestFocus();
////        Main.Main.currentStage.setMaximized(true);
//        Main.Main.currentStage.setScene(Main.Main.gameScene);
//        Main.Main.currentStage.setFullScreen(true);
    }

    public void onLeaveButtonClick(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }
}
