package ninja.hikaru.kimoppi.lunandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.TextureView;

import ninja.hikaru.kimoppi.lunandroid.support.FpsManager;
import ninja.hikaru.kimoppi.lunandroid.support.FpsMoniter;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Game implements Runnable {

    private static final String TAG = "LUNA";
    private final TextureView view;
    private final FpsManager fpsManager;
    private final FpsMoniter fpsMoniter;
    private Scene currentScene;
    private Thread thread;

    public Game(TextureView view) {
        this.view = view;
        this.fpsManager = new FpsManager();
        this.fpsMoniter = new FpsMoniter(10);

        setCurrentScene(Scene.class);
    }


    @Override
    public void run() {
        fpsManager.start();
        while (thread != null) {
            if (fpsManager.sleep()) {
                continue;
            }
            Canvas c = view.lockCanvas();
            loop(c);
            view.unlockCanvasAndPost(c);
        }
    }

    private void loop(Canvas c) {
        c.drawColor(Color.BLACK);
        currentScene.update();
        currentScene.draw(c);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(30f);
        c.drawText(String.format("%5.02fFps", fpsMoniter.show()), 100, 100, p);
    }

    public synchronized void start() {
        if (thread != null) {
            throw new RuntimeException("Game is already started.");
        }
        thread = new Thread(this);
        thread.start();
        Log.d(TAG, "Game start!");
    }

    public synchronized void stop() {
        thread = null;
        Log.d(TAG, "Game stop!");
    }

    public FpsManager getFpsManager() {
        return fpsManager;
    }

    public synchronized <T extends Scene> T setCurrentScene(Class<T> sceneClass) {
        try {
            T scene = sceneClass.newInstance();
            scene.onCreate(currentScene, this);
            this.currentScene = scene;
            return scene;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public Context getContext() {
        return view.getContext();
    }

    public int getWidth() {
        return view.getWidth();
    }

    public int getHeight() {
        return view.getHeight();
    }
}
