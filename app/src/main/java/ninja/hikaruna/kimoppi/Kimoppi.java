package ninja.hikaruna.kimoppi;

import ninja.hikaru.kimoppi.R;
import ninja.hikaruna.lunandroid.Physics;
import ninja.hikaruna.lunandroid.Resourceble;
import ninja.hikaruna.lunandroid.Sprite;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends Sprite {

    public Kimoppi() {
        x = (int) (Math.random() * 1000);
        y = (int) (Math.random() * 2000);
        w = 100;
        h = 100;

        useFeature(Resourceble.class).setResource(R.drawable.kimoppi0);

        useFeature(Physics.class);
    }

    @Override
    public void update() {
        super.update();

        useFeature(Physics.class).speedX += (Math.random() * 3) - 1.5f;
        useFeature(Physics.class).speedY += (Math.random() * 3) - 1.5f;
    }
}
