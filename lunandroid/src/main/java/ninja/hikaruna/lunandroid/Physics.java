package ninja.hikaruna.lunandroid;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Physics extends Feature {

    public float speedX, speedY;

    @Override
    public void onUpdate() {
        getSprite().x += speedX;
        getSprite().y += speedY;
    }
}
