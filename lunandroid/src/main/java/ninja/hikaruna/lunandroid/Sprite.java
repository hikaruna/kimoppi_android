package ninja.hikaruna.lunandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;

import java.util.LinkedList;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Sprite {

    public int x, y;
    public int w, h;
    Picture picture;
    Paint background;

    private LinkedList<Feature> features;
    private SpriteGroup parent;

    public Sprite() {
        picture = new Picture();
        features = new LinkedList<>();
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

    public synchronized <T extends Feature> T useFeature(Class<T> featureClass) {
        if (!containsFeature(featureClass)) {
            setFeature(featureClass);
        }

        return getFeature(featureClass);
    }

    private synchronized void setFeature(Class<? extends Feature> featureClass) {
        try {
            if (containsFeature(featureClass)) {
                throw new RuntimeException(featureClass.getSimpleName() + "is alredy enabled.");
            }
            Feature feature = featureClass.newInstance();
            resolveDependency(feature);
            feature.setSprite(this);
            features.add(0, feature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized boolean containsFeature(Class<? extends Feature> featureClass) {
        for (Feature feature : features) {
            if (feature.getClass().equals(featureClass)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public <T extends Feature> T getFeature(Class<T> featureClass) {
        for (Feature feature : features) {
            if (feature.getClass().equals(featureClass)) {
                return (T) feature;
            }
        }
        return null;
    }

    private boolean resolveDependency(Feature feature) {
        if (feature.getDepends() == null) {
            return true;
        }

        for (Class<? extends Feature> depend : feature.getDepends()) {
            if (!containsFeature(depend)) {
                setFeature(depend);
                return false;
            }
        }
        return true;
    }

    public void update() {
        for (Feature feature : features) {
            feature.onUpdate();
        }
    }

    public void draw(Canvas c) {
        if (background != null) {
            c.drawRect(getRect(), background);
        }
        for (Feature feature : features) {
            feature.onDraw(c);
        }
    }

    public void destroy() {
        for (Feature feature : features) {
            feature.onDestroy();
        }
    }

    public void onSceneSetted() {
        for (Feature feature : features) {
            feature.onSceneSetted();
        }

    }
}

