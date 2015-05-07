package ninja.hikaruna.lunandroid;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hikaru on 2015/05/08.
 */
public class ResourceManager {

    private final Resources resources;
    private Map<Integer, Picture> pictures;

    public ResourceManager(Resources resources) {
        pictures = new HashMap<>();
        this.resources = resources;
    }

    public Picture getPicture(int resId) {
        if (!pictures.containsKey(resId)) {
            throw new RuntimeException("ResourceId(" + resId + ") was not registered.");
        }
        return pictures.get(resId);
    }

    public Picture setPicture(int resId) {
        if (!pictures.containsKey(resId)) {
            Picture p = new Picture();
            Bitmap b = BitmapFactory.decodeResource(resources, resId);
            Canvas c = p.beginRecording(b.getWidth(), b.getHeight());
            c.drawBitmap(b, 0, 0, null);
            p.endRecording();

            pictures.put(resId, p);
        }
        return pictures.get(resId);
    }
}
