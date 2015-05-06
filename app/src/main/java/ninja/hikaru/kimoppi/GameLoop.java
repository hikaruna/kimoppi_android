package ninja.hikaru.kimoppi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.TextureView;

import ninja.hikaru.kimoppi.lunandroid.RootView;

/**
 * Created by hikaru on 2015/05/06.
 */
public class GameLoop implements Runnable {

    private static final String TAG = "LOOP";
    private final TextureView view;
    private final int fps;
    private final RootView rootView;
    private final Bitmap kimoppiImage;
    private boolean stop;
    private int i;

    public GameLoop(TextureView view) {
        this.view = view;
        this.fps = 30;
        this.rootView = new Kimoppi();
        kimoppiImage = BitmapFactory.decodeResource(view.getContext().getResources(), rootView.Resource());
    }


    @Override
    public void run() {
        long now = System.nanoTime();
        long next = now + 1000000000 / fps;
        while (true) {
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
            if (stop) {
                break;
            }
        }
    }

    private void loop(Canvas c) {
        Log.d(TAG, "loop" + i++);
        rootView.update();
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawRect(0, 0, view.getWidth(), view.getHeight(), p);
        c.drawBitmap(kimoppiImage, null, rootView.getRect(), p);
        c.drawBitmap(kimoppiImage, null, new Rect(100, 100, 200, 200), new Paint());
        p.setColor(Color.CYAN);
        p.setTextSize(30f);
        float j = ((float) i) * 20f;
        c.drawText("hoge", j, j, p);
    }

    public void stop() {
        stop = true;
    }
}
