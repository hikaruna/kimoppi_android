package ninja.hikaruna.kimoppi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;

import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Animatable;
import ninja.hikaruna.lunandroid.feature.RectCollider;
import ninja.hikaruna.lunandroid.feature.Resourceble;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends Sprite {

    private static Picture oval;
    private final RectCollider col;

    public Kimoppi() {
        x = 509;
        y = 500;
        w = 100;
        h = 100;

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
        Animatable anim = useFeature(Animatable.class);
        anim.setDeley(5);
        anim.setAnimation(new Object[]{
                R.drawable.kimoppi0,
                R.drawable.kimoppi1,
                R.drawable.kimoppi2,
                R.drawable.kimoppi3
        });
        col = useFeature(RectCollider.class);
        col.setGroup(0);
        col.setDebugMode(true);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
    }
}
