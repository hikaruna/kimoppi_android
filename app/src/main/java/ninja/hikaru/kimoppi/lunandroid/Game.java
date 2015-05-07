package ninja.hikaru.kimoppi.lunandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.TextureView;

import org.apache.http.message.BasicListHeaderIterator;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Game implements Runnable {

    private static final String TAG = "LUNA";
    private final TextureView view;
    private final int fps;
    private boolean stop;
    private Scene currentScene;
    private Thread thread;
    private long past;

    public Game(TextureView view) {
        this.view = view;
        this.fps = 30000;
        this.currentScene = new Scene(null);
    }


    @Override
    public void run() {
        long now = System.nanoTime();
        long next = now + 1000000000 / fps;
        while (!stop) {
            // FPSを維持する
            now = System.nanoTime();
            if (next > now) {
                try {
                    Thread.sleep((next - now) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            next += 1000000000 / fps;
            if (next < 0) {
                next += 1000000000 / fps;
            }

            Canvas c = view.lockCanvas();
            loop(c);
            view.unlockCanvasAndPost(c);
        }
        thread = null;
    }

    private void loop(Canvas c) {
        c.drawColor(Color.BLACK);
        currentScene.update();
        currentScene.draw();
        c.drawPicture(currentScene.getPicture(), currentScene.getRect());
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(30f);
        long now = System.nanoTime();
        c.drawText(String.format("%5.02fFps", 1 / ((now - past) / 1000000000f)), 100, 100, p);
        past = now;
    }

    public synchronized void start() {
        if(thread != null) {
            throw new RuntimeException("Game is already started.");
        }
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        stop = true;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
