package ninja.hikaru.kimoppi.lunandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Scene extends SpriteGroup {
    private Game game;
    private ResourceManager resources;
    private ResourceManager resourceManager;

    public Scene(Scene from) {
        super();
        resources = new ResourceManager();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Context getContext() {
        return game.getContext();
    }

    @Override
    public Scene getScene() {
        return this;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public class ResourceManager {

        private Map<Integer, Picture> pictures;

        ResourceManager() {
            super();
            pictures = new HashMap<>();
        }

        public void setResource(int resId) {
            Resources resources = getContext().getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
            Picture p = new Picture();
            Canvas c = p.beginRecording(bitmap.getWidth(), bitmap.getHeight());
            Rect dist = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            c.drawBitmap(bitmap, null, dist, null);
            p.endRecording();
            pictures.put(resId, p);
        }

        public Picture getResource(int resId) {
            if(!pictures.containsKey(resId)) {
                throw new RuntimeException("Resource was not registered.");
            }
            return pictures.get(resId);
        }

        public Picture useResource(int resId) {
            if (!pictures.containsKey(resId)) {
                setResource(resId);
            }
            return getResource(resId);
        }
    }
}
