package Object;

import Controller.GameController;
import javafx.scene.image.ImageView;


/**
 * 子彈類:是飛行物
 */
public class Bullet extends FlyingObject {
    private int speed = 3;  //移動的速度

    /** 初始化資料 */
    public Bullet(int x,int y){
        this.x = x;
        this.y = y;
        this.imageView = GameController.bulletView;
        this.image = this.imageView.getImage();
    }

    /** 移動 */
    @Override
    public void step(){
        x += speed;
    }

    /** 越界處理 */
    @Override
    public boolean outOfBounds() {
        return y<-height;
    }

    public ImageView getImageView() {
        return this.imageView;
    }
}
