package Object;

import java.util.Random;

import Controller.GameController;
import javafx.scene.image.ImageView;

/** 蜜蜂 */
public class Bee extends FlyingObject implements PointAward{
    private int xSpeed = 1;   //x座標移動速度
    private int ySpeed = 2;   //y座標移動速度
    private int awardType;    //獎勵型別

    /** 初始化資料 */
    public Bee(){
        this.imageView = GameController.beeView;
        this.image = this.imageView.getImage();
        width = (int) image.getWidth();
        height = (int) image.getHeight();
        y = -height;
        Random rand = new Random();
        x = rand.nextInt((int) GameController.WIDTH - width);
        awardType = rand.nextInt(2);   //初始化時給獎勵
    }

    /** 獲得獎勵型別 */
    public int getType(){
        return awardType;
    }

    /** 越界處理 */
    @Override
    public boolean outOfBounds() {
        return y > GameController.HEIGHT;
    }

    /** 移動，可斜著飛 */
    @Override
    public void step() {
        x += xSpeed;
        y += ySpeed;
        if (x > GameController.WIDTH - width) {
            xSpeed = -1;
        }
        if (x < 0) {
            xSpeed = 1;
        }
    }
    public ImageView getImageView() {
        return this.imageView;
    }
}
