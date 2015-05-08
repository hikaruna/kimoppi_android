package ninja.hikaruna.kimoppi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;

import ninja.hikaru.kimoppi.R;
import ninja.hikaruna.lunandroid.Animatable;
import ninja.hikaruna.lunandroid.Physics;
import ninja.hikaruna.lunandroid.Resourceble;
import ninja.hikaruna.lunandroid.Sprite;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends Sprite {

    private static Picture oval;

    public Kimoppi() {
        x = (int) (Math.random() * 1000);
        y = (int) (Math.random() * 2000);
        w = 100;
        h = 100;
        setBackground(Color.RED);


        if (oval == null) {
            Picture pic = new Picture();
            Canvas c = pic.beginRecording(1000, 1000);
            c.clipRect(new RectF(0, 0, 1000, 1000));
            Paint p = new Paint();
            c.drawColor(Color.BLACK);
            p.setColor(Color.WHITE);
            c.drawOval(new RectF(0, 0, 2000, 1000), p);
            pic.endRecording();
            oval = pic;
        }

        useFeature(Resourceble.class);
        useFeature(Animatable.class).setAnimation(new Object[]{
                R.drawable.kimoppi0,
                R.drawable.kimoppi1,
                R.drawable.kimoppi2,
                R.drawable.kimoppi3,
                oval
        });

        useFeature(Physics.class);
    }

    @Override
    public void update() {
        super.update();

        useFeature(Physics.class).speedX += (Math.random() * 1) - 0.5f;
        useFeature(Physics.class).speedY += (Math.random() * 1) - 0.5f;
    }
}
