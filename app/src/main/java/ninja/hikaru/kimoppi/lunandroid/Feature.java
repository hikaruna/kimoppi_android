package ninja.hikaru.kimoppi.lunandroid;

import android.graphics.Canvas;

/**
 * Created by hikaru on 2015/05/07.
 */
public abstract class Feature {
    private Sprite sprite;

    public Feature() {
    }

    public Class<? extends Feature>[] getDepends() {
        return new Class[]{};
    }

    public void onUpdate() {
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void onDraw(Canvas c) {
    }

    public void onDestroy() {
    }
}
