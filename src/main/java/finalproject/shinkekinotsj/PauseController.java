package finalproject.shinkekinotsj;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.animation.Animation.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseController {

    @FXML private GridPane root;
    @FXML private Button Resume;
    @FXML private Button Menu;
    @FXML private Button Leave;

    public void onResumeButtonClick(ActionEvent actionEvent) {
        System.out.println("Resume");
        final Node source = (Node) actionEvent.getSource();
        Main.gameScene.getRoot().requestFocus();
        Main.currentStage.setScene(Main.gameScene);
//        GameController.animationTimer.stop();
//        GameController.ResumeButtonPressed();

//        FXMLLoader gameFXML = new FXMLLoader(this.getClass().getResource("game.fxml"));
//        try {
//            Parent root = (Parent) gameFXML.load();
//            gameFXML.getController().ResumeButtonPressed();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    public void onMenuButtonClick(ActionEvent actionEvent) {
        System.out.println("Menu");
        final Node source = (Node) actionEvent.getSource();
        Main.menuScene.getRoot().requestFocus();
        Main.currentStage.setScene(Main.menuScene);
    }

    public void onLeaveButtonClick(ActionEvent actionEvent) {
        System.out.println("Leave");
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }
}

