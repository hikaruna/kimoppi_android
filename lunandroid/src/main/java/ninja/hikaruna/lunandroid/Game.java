package ninja.hikaruna.lunandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

import ninja.hikaruna.lunandroid.support.FpsManager;
import ninja.hikaruna.lunandroid.support.FpsMoniter;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Game implements Runnable {

    private static final String TAG = "LUNA";
    private final TextureView view;
    private final FpsManager fpsManager;
    private final FpsMoniter fpsMoniter;
    public long frameCount;
    private Scene currentScene;
    private Thread thread;

    public Game(TextureView view) {
        this.view = view;
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return getCurrentScene().onTouch(v, event);
            }
        });
        this.fpsManager = new FpsManager();
        this.fpsMoniter = new FpsMoniter(10);

        setCurrentScene(new Scene());
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
        frameCount++;
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

    public synchronized void setCurrentScene(Scene scene) {
        scene.create(currentScene, this);
        scene.resume(currentScene, this);
        this.currentScene = scene;
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

    public float getTop() {
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        return xy[1];
    }

    public float getLeft() {
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        return xy[0];
    }
}
