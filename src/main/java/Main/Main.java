package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage currentStage;
    public static Scene menuScene;
    public static Scene gameScene;
    public static Scene pauseScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader menuFxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        FXMLLoader gameFxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        FXMLLoader pauseFxmlLoader = new FXMLLoader(Main.class.getResource("pause.fxml"));

        currentStage = primaryStage;
        menuScene = new Scene(menuFxmlLoader.load());
        gameScene = new Scene(gameFxmlLoader.load());
        pauseScene = new Scene(pauseFxmlLoader.load());

        currentStage.setTitle("CyberTown2022--進擊的TSJ");
        currentStage.setMaximized(true);
//        currentStage.setResizable(false);
        currentStage.setFullScreen(true);
        currentStage.setScene(menuScene);
        currentStage.setOnCloseRequest(e -> Platform.exit());
//        currentStage.setFullScreenExitHint("");
        currentStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}