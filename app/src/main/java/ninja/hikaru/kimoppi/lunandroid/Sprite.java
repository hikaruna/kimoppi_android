package ninja.hikaru.kimoppi.lunandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.RectF;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Sprite {

    public int x, y;
    public int w, h;
    Picture picture;

    private Map<Class<? extends Feature>, Feature> features;
    private SpriteGroup parent;

    public Sprite() {
        picture = new Picture();
        features = new HashMap<>();
    }

    public void setParent(SpriteGroup parent) {
        this.parent = parent;
    }

    public SpriteGroup getParent() {
        return parent;
    }

    public Scene getScene() {
        return getParent().getScene();
    }

    public Context getContext() {
        return getParent().getContext();
    }

    public float getTop() {
        return x - (float) h / 2;
    }

    public float getBottom() {
        return x + (float) h / 2;
    }

    public float getLeft() {
        return y - (float) w / 2;
    }

    public float getRight() {
        return y + (float) w / 2;
    }

    public RectF getRect() {
        return new RectF(getLeft(), getTop(), getRight(), getBottom());
    }

    public synchronized <T extends Feature> T useFeature(Class<T> featureClass) {
        if (!features.containsKey(featureClass)) {
            setFeature(featureClass);
        }
        return (T) features.get(featureClass);
    }

    private void setFeature(Class<? extends Feature> featureClass) {
        try {
            Feature feature = featureClass.newInstance();
            checkDependencyResolution(feature);
            feature.setSprite(this);
            features.put(featureClass, feature);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void checkDependencyResolution(Feature feature) {
        for (Class depend : feature.getDepends()) {
            if (!features.containsKey(depend)) {
                String f = "Dependency resolution failed. %s depends %s.";
                String msg = String.format(f, feature.getClass().getSimpleName(), depend.getSimpleName());
                throw new RuntimeException(msg);
            }
        }
    }

    public boolean containFeature(Class<? extends Feature> featureClass) {
        return features.containsKey(featureClass);
    }

    public void update() {
        for (Feature feature : features.values()) {
            feature.onUpdate();
        }
    }

    public void draw(Canvas c) {
        for (Feature feature : features.values()) {
            feature.onDraw(c);
        }
    }

    public void destroy() {
        for (Feature feature : features.values()) {
            feature.onDestroy();
        }
    }

}

