package ninja.hikaruna.kimoppi.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import ninja.hikaruna.kimoppi.R;
import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Animatable;
import ninja.hikaruna.lunandroid.feature.Controllable;
import ninja.hikaruna.lunandroid.feature.Physics;
import ninja.hikaruna.lunandroid.feature.RectCollider;
import ninja.hikaruna.lunandroid.feature.Resourceble;
import ninja.hikaruna.lunandroid.util.Vector2D;

public class Kimoppi extends Sprite {

    private static Picture oval;

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

        RectCollider col = useFeature(RectCollider.class);
        col.setGroup(0);
        col.setDebugMode(true);

        Physics physics = useFeature(Physics.class);
        physics.setResistance(0.03F);
        physics.setDebugMode(true);

        useFeature(Controllable.class).setController(new Controllable.Controller() {
            @Override
            public void onControll(MotionEvent event) {
                Log.d("Ctrl", "" + getGame().frameCount + "F: " + event.toString());
                float eX = event.getX() - getGame().getLeft();
                float eY = event.getY() - getGame().getTop();

                Vector2D eV = new Vector2D(eX, eY); // タッチしたところ
                Vector2D position = new Vector2D(getAbsoluteX(), getAbsoluteY()); // きもッピの場所
                Physics p = useFeature(Physics.class);
                Vector2D speed = new Vector2D(p.speedX, p.speedY).multiplication(30F);
                Vector2D defaultDist = position.addition(speed); // なにもしない時のきもっぴの到着点
                Vector2D subV = eV.subtraction(defaultDist); // 到着点とタッチしたところの差のベクトル
                Vector2D subV2 = subV.division(400);


                p.speedX += subV2.getX();
                p.speedY += subV2.getY();
            }
        });
    }
}
