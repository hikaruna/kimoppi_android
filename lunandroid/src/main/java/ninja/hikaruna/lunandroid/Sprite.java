package ninja.hikaruna.lunandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
    Paint background;

    private Map<Class<? extends Feature>, Feature> features;
    private SpriteGroup parent;

    public Sprite() {
        picture = new Picture();
        features = new HashMap<>();
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

    @SuppressWarnings("unchecked")
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkDependencyResolution(Feature feature) {
        if (feature.getDepends() == null) {
            return;
        }

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
        if(background != null) {
            c.drawRect(getRect(), background);
        }
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

