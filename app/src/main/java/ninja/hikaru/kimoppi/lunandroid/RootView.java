package ninja.hikaru.kimoppi.lunandroid;

import android.graphics.RectF;

import ninja.hikaru.kimoppi.R;

/**
 * Created by hikaru on 2015/05/06.
 */
public abstract class RootView {

    int x, y;
    int w, h;

    public float getTop() {
        return x - (float)h/2;
    }

    public float getBottom() {
        return x + (float)h/2;
    }

    public float getLeft() {
        return y - (float)w/2;
    }

    public float getRight() {
        return y + (float)w/2;
    }

    public RectF getRect() {
        return new RectF(getLeft(), getTop(), getRight(), getBottom());
    }

    public abstract int Resource();
    public abstract void update();
}
