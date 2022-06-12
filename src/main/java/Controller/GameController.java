package Controller;


import Main.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import Object.FlyingObject;
import Object.Bullet;
import Object.Hero;
import Object.Bee;
import Object.Airplane;
import Object.PointAward;
import Object.Enemy;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    @FXML private AnchorPane root;
    @FXML private Button Pause;
    @FXML private Pane gamePane;

    public static Group groupRoot;

    static Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    public static final double WIDTH = screenBounds.getWidth(); // 面板寬
    public static final double HEIGHT = screenBounds.getHeight(); // 面板高
    /** 遊戲的當前狀態: START RUNNING PAUSE GAME_OVER */
    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static final int GAME_OVER = 3;

    private int score = 0; // 得分
    private Timer timer; // 定時器
    private int interval = 1000 / 100; // 時間間隔(毫秒)
    public int flyEnteredIndex = 0; // 飛行物入場計數
    public int shootIndex = 0; // 射擊計數

    public static Image background;
    public static Image start;
    public static Image pause;
    public static Image gameOver;
    public static ImageView airplaneView;
    public static ImageView beeView;
    public static ImageView bulletView;
    public static ImageView hero0View;
    public static ImageView hero1View;

    static {
        airplaneView = new ImageView(new Image("amongUs.png"));
        beeView = new ImageView(new Image("bee.png"));
        bulletView = new ImageView(new Image("bullet.png"));
        hero0View = new ImageView(new Image("hero0.png"));
        hero1View = new ImageView(new Image("hero1.png"));
    }



    private FlyingObject[] flying = {}; // 敵機陣列
    private Bullet[] bullets = {}; // 子彈陣列
    private Hero hero = new Hero(); // 英雄機
    Scene currentScene = Main.currentStage.getScene();

    public GameController() {
        groupRoot = new Group();
        groupRoot.setFocusTraversable(true);
        groupRoot.requestFocus();
//        root.setFocusTraversable(true);
//        root.requestFocus();

        gamePane = new Pane();
        gameStart();
    }


    /** 啟動執行程式碼 */
    @FXML
    public void gameStart() {
//        AnimationTimer animationTimer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {        // public void handle(long now) {
//
//            }
//        };
//        animationTimer.start();
//        animationTimer.stop();

//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> action()));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.playFromStart();

        groupRoot.getChildren().addAll(hero0View);
        gamePane.getChildren().add(hero0View);
        hero0View.setLayoutX(100);
        hero0View.setLayoutY(100);
        timer = new Timer(); // 主流程控制
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                if (state == RUNNING) { // 執行狀態
                    enterAction(); // 飛行物入場
                    stepAction(); // 走一步
                    shootAction(); // 英雄機射擊
                    bangAction(); // 子彈打飛行物
                    outOfBoundsAction(); // 刪除越界飛行物及子彈
                    checkGameOverAction(); // 檢查遊戲結束
//                }
//                repaint(); // 重繪，呼叫paint()方法
            }

        }, interval, interval);
    }

    /** 飛行物入場 */
    public void enterAction() {
        flyEnteredIndex++;
        if (flyEnteredIndex % 40 == 0) { // 400毫秒生成一個飛行物--10*40
            FlyingObject obj = generateFlyingObject(); // 隨機生成一個飛行物
            flying = Arrays.copyOf(flying, flying.length + 1);
            flying[flying.length - 1] = obj;
            gamePane.getChildren().add(obj.getImageView());
        }
        System.out.println("enterAction");
    }

    /** 走一步 */
    public void stepAction() {
        for (FlyingObject f : flying) { // 飛行物走一步
            f.step();
        }

        for (Bullet b : bullets) { // 子彈走一步
            b.step();
        }
        hero.step(); // 英雄機走一步
        System.out.println("stepAction");
    }

    /** 飛行物走一步 */
    public void flyingStepAction() {
        for (FlyingObject f : flying) {
            f.step();
        }
        System.out.println("flyingStepAction");
    }

    public void onKeyPressed(KeyEvent key) {
        System.out.println(key.getCode());
        switch (key.getCode()) {
            case UP, W -> hero.moveUp();
            case DOWN, S -> hero.moveDown();
            case LEFT, A -> hero.moveLeft();
            case RIGHT, D -> hero.moveRight();
        }

    }
    public void onMouseClicked() {
        System.out.println("mouse clicked!");
    }
    public void onMouseMoved() {
        System.out.println("mouse moved!");
    }
    public void onMouseEntered() {
        if (state == PAUSE) {
            state = RUNNING;
        }
        System.out.println("mouse entered!");
    }
    public void onMouseExited() {
        if (state == RUNNING) {
            state = PAUSE;
        }
        System.out.println("mouse exited!");
    }

    /** 射擊 */
    public void shootAction() {
        shootIndex++;
        if (shootIndex % 30 == 0) { // 300毫秒發一顆
            Bullet[] bs = hero.shoot(); // 英雄打出子彈
            bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // 擴容
            System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
                    bs.length); // 追加陣列
        }
        System.out.println("shootAction");
    }

    /** 子彈與飛行物碰撞檢測 */
    public void bangAction() {
        for (Bullet b : bullets) { // 遍歷所有子彈
            bang(b); // 子彈和飛行物之間的碰撞檢查
        }
        System.out.println("bangAction");
    }

    /** 刪除越界飛行物及子彈 */
    public void outOfBoundsAction() {
        int index = 0; // 索引
        FlyingObject[] flyingLives = new FlyingObject[flying.length]; // 活著的飛行物
        for (FlyingObject f : flying) {
            if (!f.outOfBounds()) {
                flyingLives[index++] = f; // 不越界的留著
            }
        }
        flying = Arrays.copyOf(flyingLives, index); // 將不越界的飛行物都留著

        index = 0; // 索引重置為0
        Bullet[] bulletLives = new Bullet[bullets.length];
        for (Bullet b : bullets) {
            if (!b.outOfBounds()) {
                bulletLives[index++] = b;
            }
        }
        bullets = Arrays.copyOf(bulletLives, index); // 將不越界的子彈留著
        System.out.println("outOfBoundsAction");
    }

    /** 檢查遊戲結束 */
    public void checkGameOverAction() {
        if (isGameOver()) {
            state = GAME_OVER; // 改變狀態
        }
        System.out.println("checkGameOverAction");
    }

    /** 檢查遊戲是否結束 */
    public boolean isGameOver() {

        for (int i = 0; i < flying.length; i++) {
            int index = -1;
            FlyingObject obj = flying[i];
            if (hero.hit(obj)) { // 檢查英雄機與飛行物是否碰撞
                hero.subtractLife(); // 減命
                hero.setDoubleFire(0); // 雙倍火力解除
                index = i; // 記錄碰上的飛行物索引
            }
            if (index != -1) {
                FlyingObject t = flying[index];
                flying[index] = flying[flying.length - 1];
                flying[flying.length - 1] = t; // 碰上的與最後一個飛行物交換

                flying = Arrays.copyOf(flying, flying.length - 1); // 刪除碰上的飛行物
            }
        }
        System.out.println("isGameOver");
        return hero.getLife() <= 0;
    }

    /** 子彈和飛行物之間的碰撞檢查 */
    public void bang(Bullet bullet) {
        int index = -1; // 擊中的飛行物索引
        for (int i = 0; i < flying.length; i++) {
            FlyingObject obj = flying[i];
            if (obj.shootBy(bullet)) { // 判斷是否擊中
                index = i; // 記錄被擊中的飛行物的索引
                break;
            }
        }
        if (index != -1) { // 有擊中的飛行物
            FlyingObject one = flying[index]; // 記錄被擊中的飛行物

            FlyingObject temp = flying[index]; // 被擊中的飛行物與最後一個飛行物交換
            flying[index] = flying[flying.length - 1];
            flying[flying.length - 1] = temp;

            flying = Arrays.copyOf(flying, flying.length - 1); // 刪除最後一個飛行物(即被擊中的)

            // 檢查one的型別(敵人加分，獎勵獲取)
            if (one instanceof Enemy e) { // 檢查型別，是敵人，則加分
//                Enemy e = (Enemy) one; // 強制型別轉換
                score += e.getScore(); // 加分
            } else { // 若為獎勵，設定獎勵
                PointAward a = (PointAward) one;
                int type = a.getType(); // 獲取獎勵型別
                switch (type) {
                    case PointAward.DOUBLE_FIRE -> hero.addDoubleFire(); // 設定雙倍火力
                    case PointAward.LIFE -> hero.addLife(); // 設定加命
                }
            }
        }
        System.out.println("bang");
    }

    /**
     * 隨機生成飛行物
     *
     * @return 飛行物物件
     */
    public static FlyingObject generateFlyingObject() {
        System.out.println("generateFlyingObject");
        Random random = new Random();
        int type = random.nextInt(20); // [0,20)
        if (type < 4) {
            return new Bee();
        } else {
            return new Airplane();
        }
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {
        state = PAUSE;
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(Main.pauseScene);
        stage.setFullScreen(true);

//        Main.Main.pauseScene.getRoot().requestFocus();
////        Main.Main.currentStage.setMaximized(true);
//        Main.Main.currentStage.setScene(Main.Main.pauseScene);
//        Main.Main.currentStage.setFullScreen(true);
    }

}
