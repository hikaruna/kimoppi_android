package ninja.hikaruna.lunandroid.feature;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Physics extends Feature {

    public float speedX, speedY;
    private Float speedLimitX;
    private Float speedLimitY;
    private Float resistance = 0F;


    @Override
    public void onUpdate() {
        if (speedLimitX != null) {
            if (speedX < -speedLimitX) {
                speedX = -speedLimitX;
            } else if (speedX > speedLimitX) {
                speedX = speedLimitX;
            }
        }

        if (speedLimitY != null) {
            if (speedY < -speedLimitY) {
                speedY = -speedLimitY;
            } else if (speedY > speedLimitY) {
                speedY = speedLimitY;
            }
        }

        speedX *= 1 - resistance;
        speedY *= 1 - resistance;

        getSprite().x += speedX;
        getSprite().y += speedY;
    }

    public void setSpeedLimitX(float speedLimitX) {
        this.speedLimitX = speedLimitX;
    }

    public void setSpeedLimitY(float speedLimitY) {
        this.speedLimitY = speedLimitY;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }
}
