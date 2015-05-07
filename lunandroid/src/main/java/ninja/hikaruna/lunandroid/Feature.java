package ninja.hikaruna.lunandroid;

import android.graphics.Canvas;
import android.support.annotation.Nullable;

/**
 * Created by hikaru on 2015/05/07.
 */
public abstract class Feature {
    private Sprite sprite;

    public Feature() {
    }

    @Nullable
    public Class<? extends Feature>[] getDepends() {
        return null;
    }

    public void onUpdate() {
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void onDraw(Canvas c) {
    }

    public void onDestroy() {
    }
}
