package finalproject.shinkekinotsj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.*;

public class Main extends Application {
    public static Stage currentStage;
    public static Scene menuScene;
    public static Scene pauseScene;
    public static Scene flashScene;
    public static GameController gameController = new GameController();
    public static Scene descriptionScene;
    public static Scene gameScene = new Scene(gameController);

//    public class CloseClipWhenDone implements LineListener {
//        @Override
//        public void update(LineEvent event) {
//            if (event.getType().equals(LineEvent.Type.STOP)) {
//                Line soundClip = event.getLine();
//                soundClip.close();
//            }
//        }
//    }

    @Override
    public void start(Stage primaryStage) throws IOException {

//        try {
//            AudioInputStream audioStream =  AudioSystem.getAudioInputStream(new File("move.wav"));
//            Clip clip = AudioSystem.getClip();
//            clip.addLineListener(new CloseClipWhenDone());
//            clip.open(audioStream);
//            clip.start();
//        }
//        catch (LineUnavailableException | UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        }


        currentStage = primaryStage;
        FXMLLoader menuFxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        FXMLLoader pauseFxmlLoader = new FXMLLoader(Main.class.getResource("pause.fxml"));
        menuScene = new Scene(menuFxmlLoader.load());
        pauseScene = new Scene(pauseFxmlLoader.load());
        currentStage.setScene(menuScene);
        currentStage.setTitle("CyberTown 2022--進擊的TSJ");
        currentStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

