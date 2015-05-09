package ninja.hikaruna.lunandroid.feature;

import android.graphics.Canvas;
import android.support.annotation.Nullable;

import ninja.hikaruna.lunandroid.Scene;
import ninja.hikaruna.lunandroid.Sprite;

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

    public void onSpriteSetted(Sprite sprite) {
    }

    public void onSceneSetted(Scene scene) {
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
