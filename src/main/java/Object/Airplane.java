package Object;

import java.util.Random;

import Controller.GameController;
import javafx.scene.image.ImageView;


// 敵飛機: 是飛行物，也是敵人

public class Airplane extends FlyingObject implements Enemy {
    private int speed = 3;  //移動步驟

    /** 初始化資料 */
    public Airplane(){
        this.imageView = GameController.airplaneView;
        this.image = this.imageView.getImage();
        width = (int) image.getWidth();
        height = (int) image.getHeight();
        y = -height;
        Random rand = new Random();
        x = rand.nextInt((int) GameController.WIDTH - width);
    }

    /** 獲取分數 */
    @Override
    public int getScore() {
        return 5;
    }

    /** //越界處理 */
    @Override
    public boolean outOfBounds() {
        return y > GameController.HEIGHT;
    }

    /** 移動 */
    @Override
    public void step() {
        x -= speed;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

}