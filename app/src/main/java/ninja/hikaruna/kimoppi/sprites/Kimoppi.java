package ninja.hikaruna.kimoppi.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Vector;

import ninja.hikaruna.kimoppi.R;
import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Animatable;
import ninja.hikaruna.lunandroid.feature.Controllable;
import ninja.hikaruna.lunandroid.feature.Physics;
import ninja.hikaruna.lunandroid.feature.RectCollider;
import ninja.hikaruna.lunandroid.feature.Resourceble;
import ninja.hikaruna.lunandroid.util.Vector2D;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends Sprite {

    private static Picture oval;
    private final float speed;

    public Kimoppi() {
        x = 509;
        y = 500;
        w = 100;
        h = 100;

        speed = 3.0F;

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

        RectCollider col = useFeature(RectCollider.class);
        col.setGroup(0);
        col.setDebugMode(true);

        useFeature(Physics.class);

        useFeature(Controllable.class).setController(new Controllable.Controller() {
            @Override
            public void onControll(MotionEvent event) {
                Log.d("Ctrl", "" + getGame().frameCount + "F: " + event.toString());
                float tX = event.getX() - getGame().getLeft();
                float tY = event.getY() - getGame().getTop();

                Vector2D v = new Vector2D(tX, tY);
                Vector2D selfV = new Vector2D(getAbsoluteX(), getAbsoluteY());
                Vector2D v2 = v.subtraction(selfV);

                Vector2D v3 = v2.getNomalize();
                Vector2D v4 = v3.multiplication(2);

                Physics p = useFeature(Physics.class);
                p.speedX += v4.getX();
                p.speedY += v4.getY();
                if(p.speedX > 30F) {
                    p.speedX = 30F;
                }else if(p.speedX < -30F) {
                    p.speedX = -30F;
                }
                if(p.speedY > 30F) {
                    p.speedY = 30F;
                }else if(p.speedY < -30F) {
                    p.speedY = -30F;
                }
            }
        });
    }
}
