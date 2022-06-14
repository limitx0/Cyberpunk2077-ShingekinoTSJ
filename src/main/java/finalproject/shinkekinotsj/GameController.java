package finalproject.shinkekinotsj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Random;

public class GameController extends Pane {

    public static final double WIDTH = 1280;
    public static final double HEIGHT = 720;

    public Image heroImage;
    public Image weaponImage;
    public Image TSJImage;
    public Image badPoliceImage;
    public Node hero0;
    public ArrayList<Node> weapons = new ArrayList<>();
    public ArrayList<Node> villains = new ArrayList<>();
    public Group board;
    public Label livesLabel;
    public Label scoreLabel;
    public Label gameOver;

    public int speedWeapon = 10;
    public int speedVillain = 4;
    public int scoreToSpeedUP = 100;
    public int villainCounter = scoreToSpeedUP - 1;
    public int level = 1;
    public int score = 0;
    public int lives = 3;
    public int heroSpeed = 5;
    boolean goUp, goDown, goLeft, goRight, shooting;
    public static boolean isPaused = false;

    public static AnimationTimer animationTimer;
    public Scene scene;



    public void start(Stage stage) {
        heroImage = new Image("PoliceWang.png", 150, 200, false, false);
        hero0 = new ImageView(heroImage);
        weaponImage = new Image("bullet.png", 100, 50, false, false);
        TSJImage = new Image("TSJaskYouToBlow.png", 150, 200, false, false);
        badPoliceImage = new Image("PoliceAskYouToDelete.png", 150, 200, false, false);

        scoreLabel = new Label();
        scoreLabel.setText("Score: " + score);
        scoreLabel.setFont(Font.font("Eras Bold ITC", FontWeight.BOLD, FontPosture.REGULAR, 32));
        scoreLabel.setStyle("-fx-text-fill: white");
        scoreLabel.setLayoutX(300);
        scoreLabel.setLayoutY(30);
        livesLabel = new Label();
        livesLabel.setText("Lives: " + lives);
        livesLabel.setFont(Font.font("Eras Bold ITC", FontWeight.BOLD, FontPosture.REGULAR, 32));
        livesLabel.setStyle("-fx-text-fill: white");
        livesLabel.setLayoutX(800);
        livesLabel.setLayoutY(30);

        gameOver = new Label("GameOver!!!");
        gameOver.setLayoutX(WIDTH/2 - 50);
        gameOver.setLayoutY(HEIGHT/2);
        gameOver.setFont(Font.font("Eras Bold ITC", FontWeight.BOLD, FontPosture.REGULAR, 32));
        gameOver.setStyle("-fx-text-fill: red");
        gameOver.setVisible(false);

        board = new Group();
        board.getChildren().addAll(hero0, scoreLabel, livesLabel, gameOver);
        moveHeroTo(100, 250);

        Main.gameScene = new Scene(board, WIDTH, HEIGHT, Color.LIME);
        Main.gameScene.setOnKeyPressed(this::onKeyPressed);
        Main.gameScene.setOnKeyReleased(this::onKeyReleased);
        Main.gameScene.setOnMouseClicked(this::onMouseClicked);
        Main.gameScene.setOnMouseMoved(this::onMouseMoved);
        Main.gameScene.setOnMouseEntered(this::onMouseEntered);
        Main.gameScene.setOnMouseExited(this::onMouseExited);

        stage.setTitle("CyberTown2022--進擊的TSJ");
        stage.getIcons().add(new Image("icon.png"));

        stage.setOnCloseRequest(e -> Platform.exit());
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                System.out.println("animationTimer");
                int dx = 0, dy = 0;
                if (goUp) dy -= heroSpeed;
                if (goDown) dy += heroSpeed;
                if (goLeft) dx -= heroSpeed;
                if (goRight) dx += heroSpeed;

                villainCounter++;
                if (villainCounter%scoreToSpeedUP == 0) {
                    if (scoreToSpeedUP > 20) {
                        scoreToSpeedUP -= level;
                    }
                    Random random = new Random();
                    Node newVillain;
                    if (random.nextInt(2) == 0) {
                        newVillain = new ImageView(TSJImage);
                    }
                    else {
                        newVillain = new ImageView(badPoliceImage);
                    }
                    newVillain.relocate(WIDTH, (int) (Math.random()*(HEIGHT - newVillain.getBoundsInLocal().getHeight())));
                    villains.add(newVillain);
                    board.getChildren().add(newVillain);
                }

                moveHeroTo(hero0.getLayoutX() + dx, hero0.getLayoutY() + dy);
                shootWeapon(speedWeapon);
                moveVillain(speedVillain);
                checkBulletHit();
                checkTSJHit();

                if (lives == 0) {
                    gameOver.setVisible(true);
                    this.stop();
                }
            }
        };
        animationTimer.start();
    }

    public void ResumeButtonPressed() {
        animationTimer.start();
    }

    public void initialize() {
        scoreToSpeedUP = 100;
        villainCounter = scoreToSpeedUP - 1;
        score = 0;
        lives = 3;
        scoreLabel.setText("Score: " + score);
        livesLabel.setText("Lives: " + lives);
        for (Node w: weapons) {
            board.getChildren().remove(w);
        }
        for (Node v: villains) {
            board.getChildren().remove(v);
        }
        weapons.clear();
        villains.clear();
        gameOver.setVisible(false);
    }

    public void moveHeroTo(double x, double y) {
//        System.out.println("moveHeroTo " + x + " " + y);
        if (x >= 0 && x <= WIDTH - hero0.getBoundsInLocal().getWidth() &&
                y >= 0 && y <= HEIGHT - hero0.getBoundsInLocal().getHeight()) {
            hero0.relocate(x, y);
        }
    }

    public void moveVillain(int speedVillain) {
//        System.out.println("moveVillain");
        Iterator<Node> iterator = villains.iterator();
        while (iterator.hasNext()) {
            Node v = iterator.next();
            if (v.getLayoutX() > -v.getBoundsInLocal().getWidth()) {
                v.relocate(v.getLayoutX() - speedVillain, v.getLayoutY());
            }
            else {
                board.getChildren().remove(v);
                iterator.remove();
                lives--;
                livesLabel.setText("Lives: " + lives);
            }
        }
    }

    public void shootWeapon(int speedWeapon) {
//        System.out.println("shootWeapon");
        Iterator<Node> iterator = weapons.iterator();
        while (iterator.hasNext()) {
            Node w = iterator.next();
            if (w.getLayoutX() < WIDTH) {
                w.relocate(w.getLayoutX() + speedWeapon, w.getLayoutY());
            }
            else {
                iterator.remove();
            }
        }
    }

    public void checkBulletHit() {
//        System.out.println("checkBulletHit");
        Iterator<Node> iteratorWeapons = weapons.iterator();
        Iterator<Node> iteratorVillains;
        while (iteratorWeapons.hasNext()) {
            Node w = iteratorWeapons.next();
            iteratorVillains = villains.iterator();
            while (iteratorVillains.hasNext()) {
                Node v = iteratorVillains.next();
                if (w.getBoundsInParent().intersects(v.getBoundsInParent()) ) {
                    board.getChildren().remove(v);
                    iteratorVillains.remove();
                    board.getChildren().remove(w);
                    iteratorWeapons.remove();
                    score++;
                    scoreLabel.setText("Scores: " + score);
                }
            }
        }
    }

    public void checkTSJHit() {
        Iterator<Node> iterator = villains.iterator();
        while (iterator.hasNext()) {
            Node v = iterator.next();
            if (v.getBoundsInParent().intersects(hero0.getBoundsInParent()) ) {
                board.getChildren().remove(v);
                iterator.remove();
                lives--;
                livesLabel.setText("Lives: " + lives);
            }
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode() + " pressed");
        switch (keyEvent.getCode()) {
            case UP, W -> goUp = true;
            case DOWN, S -> goDown = true;
            case LEFT, A -> goLeft = true;
            case RIGHT, D -> goRight = true;
            case SPACE, J -> {
                if (!shooting) {
                    Node newWeapon = new ImageView(weaponImage);
                    newWeapon.relocate(hero0.getLayoutX() + hero0.getBoundsInLocal().getWidth(),
                            hero0.getLayoutY() + hero0.getBoundsInLocal().getWidth()/2);
                    weapons.add(newWeapon);
                    board.getChildren().add(newWeapon);
                    shooting = true;
                }
            }
            case P -> {
                animationTimer.stop();
                Main.currentStage.setScene(Main.pauseScene);
            }
            case ENTER -> animationTimer.start();
            case R -> {
                initialize();
                animationTimer.start();
            }
            case Q -> {
                System.out.println("Leave");
                Platform.exit();
                System.exit(0);
            }
        }
    }
    public void onKeyReleased(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode() + " released");
        switch (keyEvent.getCode()) {
            case UP, W -> goUp = false;
            case DOWN, S -> goDown = false;
            case LEFT, A -> goLeft = false;
            case RIGHT, D -> goRight = false;
            case SPACE, J -> shooting = false;
        }
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        System.out.println("mouse clicked!");
    }
    public void onMouseMoved(MouseEvent mouseEvent) {
        System.out.println("mouse moved!");
    }
    public void onMouseEntered(MouseEvent mouseEvent) {
        System.out.println("mouse entered!");
    }
    public void onMouseExited(MouseEvent mouseEvent) {
        System.out.println("mouse exited!");
    }

}