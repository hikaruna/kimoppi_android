package ninja.hikaruna.lunandroid.feature;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.LinkedList;
import java.util.List;

import ninja.hikaruna.lunandroid.CollisionManager;
import ninja.hikaruna.lunandroid.Scene;
import ninja.hikaruna.lunandroid.Sprite;

/**
 * Created by hikaru on 2015/05/09.
 */
public class RectCollider extends AbstractCollider {

    private final Paint paint;

    public RectCollider() {
        super();
        this.paint = new Paint();
    }

    @Override
    public boolean check(AbstractCollider c2) {
        return getSprite().getRect().intersect(c2.getSprite().getRect());
    }

    @Override
    public void onDrawArea(Canvas c, boolean hit) {
        if(hit) {
            paint.setColor(Color.argb(127,255,255,255));
        }else {
            paint.setColor(Color.argb(127,0,255,0));
        }
        c.drawRect(getSprite().getRect(), paint);
    }
}
