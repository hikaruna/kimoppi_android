package ninja.hikaru.kimoppi;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import ninja.hikaru.kimoppi.lunandroid.Physics;
import ninja.hikaru.kimoppi.lunandroid.Sprite;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends Sprite {

    private Bitmap resource;

    public Kimoppi(Bitmap bitmap) {
        x = (int) (Math.random() * 1000);
        y = (int) (Math.random() * 2000);
        w = 100;
        h = 100;
        resource = bitmap;

        useFeature(Physics.class);
    }

    @Override
    public void update() {
        super.update();

        useFeature(Physics.class).speedX += (Math.random() * 3) - 1.5f;
        useFeature(Physics.class).speedY += (Math.random() * 3) - 1.5f;
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        c.drawBitmap(resource, null, getRect(), null);
    }
}
