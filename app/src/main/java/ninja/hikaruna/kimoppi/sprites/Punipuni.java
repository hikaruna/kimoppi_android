package ninja.hikaruna.kimoppi.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import ninja.hikaruna.lunandroid.ControllEvent;
import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Controllable;

/**
 * Created by hikaru on 2015/07/20.
 */
public class Punipuni extends Sprite {

    private static final int HIDE_COUNT_MAX = 5;
    private final Paint p;
    private int hideCount;

    public Punipuni() {
        p = new Paint();
        p.setColor(Color.argb(127,255,255,255));
        w = 100;
        h = 100;
        hideCount = HIDE_COUNT_MAX;
        hide();

        useFeature(Controllable.class).setController(new Controllable.Controller() {
            @Override
            public void onControll(ControllEvent event) {
                x = event.getX();
                y = event.getY();
                show();
                hideCount = HIDE_COUNT_MAX;
            }
        });
    }

    @Override
    public void onUpdate() {
        if(!isHide()) {
            if (hideCount-- < 0) {
                hide();
            }
        }
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawArc(getAbsoluteRect(), 0, 360, true, p);
    }
}
