package ninja.hikaru.kimoppi.lunandroid;

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

    public Sprite() {
        picture = new Picture();
        features = new HashMap<>();
    }

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

    public synchronized <T extends Feature> T useFeature(Class<T> featureClass) {
        if(!features.containsKey(featureClass)) {
            try {
                Feature feature = featureClass.newInstance();
                feature.setSprite(this);
                features.put(featureClass, feature);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) features.get(featureClass);
    }

    public void update() {
        for(Feature feature : features.values()) {
            feature.onUpdate();
        }
    }

    public void draw() {
        Canvas c = picture.beginRecording(w, h);
        onDraw(c);
        picture.endRecording();
    }

    public void onDraw(Canvas c) {
        for(Feature feature : features.values()) {
            feature.onDraw(c);
        }
    }

    public void draw2(Canvas c) {
        for(Feature feature : features.values()) {
            feature.onDraw(c);
        }
    }

    Picture getPicture() {
        return picture;
    }
}
