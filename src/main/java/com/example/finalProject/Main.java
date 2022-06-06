package com.example.finalProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage currentStage;
    public static Scene menuScene;
    public static Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader menuFxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        FXMLLoader gameFxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));

        currentStage = primaryStage;
        menuScene = new Scene(menuFxmlLoader.load());
        gameScene = new Scene(gameFxmlLoader.load());
        currentStage.setTitle("CyberTown2022--進擊的TSJ");
        currentStage.setMaximized(true);
        currentStage.setFullScreen(true);
        currentStage.setScene(menuScene);
        currentStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}