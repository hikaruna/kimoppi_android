package ninja.hikaruna.lunandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.LinkedList;

import ninja.hikaruna.lunandroid.feature.Feature;
import ninja.hikaruna.lunandroid.support.FeatureManager;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Sprite {

    public int x, y;
    public int w, h;
    Paint background;

    private SpriteGroup parent;
    private FeatureManager featureManager;

    public Sprite() {
        featureManager = new FeatureManager(this);
    }

    public SpriteGroup getParent() {
        return parent;
    }

    public void setParent(SpriteGroup parent) {
        this.parent = parent;
    }

    public Scene getScene() {
        return getParent().getScene();
    }

    public Context getContext() {
        return getParent().getContext();
    }

    public float getTop() {
        return y - (float) h / 2;
    }

    public float getBottom() {
        return y + (float) h / 2;
    }

    public float getLeft() {
        return x - (float) w / 2;
    }

    public float getRight() {
        return x + (float) w / 2;
    }

    public RectF getRect() {
        return new RectF(getLeft(), getTop(), getRight(), getBottom());
    }

    public void setBackground(int background) {
        this.background = new Paint();
        this.background.setColor(background);
    }

    public void update() {
        featureManager.update();
    }

    public void draw(Canvas c) {
        if (background != null) {
            c.drawRect(getRect(), background);
        }
        featureManager.draw(c);
    }

    public void destroy() {
        featureManager.destroy();
    }

    public void onSceneSetted(Scene scene) {
        featureManager.onSceneSetted(scene);
    }

    public <T extends Feature> T useFeature(Class<T> featureClass) {
        return featureManager.useFeature(featureClass);
    }

    public FeatureManager getFeatureManager() {
        return featureManager;
    }
}

