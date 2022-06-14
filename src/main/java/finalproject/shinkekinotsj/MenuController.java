package finalproject.shinkekinotsj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

import static finalproject.shinkekinotsj.Main.currentStage;

public class MenuController {
    public Button Leave;
    public Button Start;
    public GridPane root;

    @FXML
    public void onStartPressed() {
        Main.gameController.start(currentStage);
        currentStage.setScene(Main.gameScene);
    }

    @FXML

    public void onExitPressed() {
        currentStage.close();
    }
}
