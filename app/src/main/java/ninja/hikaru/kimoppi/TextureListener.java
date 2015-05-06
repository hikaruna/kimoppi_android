package ninja.hikaru.kimoppi;

import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.TextureView;

/**
 * Created by hikaru on 2015/05/06.
 */
public class TextureListener implements TextureView.SurfaceTextureListener {

    private static final String TAG = "Kimo";
    private final MainActivity context;
    private GameLoop gameLoop;

    public TextureListener(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.d(TAG, "onSufaceTextureAvailable");
        gameLoop = new GameLoop(context.getView());
        new Thread(gameLoop).start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.d(TAG, "onSurfaceTextureSizeChanged");

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.d(TAG, "onSurfaceTextureDestroyed");
        gameLoop.stop();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Log.d(TAG, "onSurfaceTextureUpdated");
    }

}
