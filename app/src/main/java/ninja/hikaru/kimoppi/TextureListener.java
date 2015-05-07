package ninja.hikaru.kimoppi;

import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.TextureView;

import ninja.hikaru.kimoppi.MainActivity;
import ninja.hikaru.kimoppi.lunandroid.Game;

/**
 * Created by hikaru on 2015/05/06.
 */
public class TextureListener implements TextureView.SurfaceTextureListener {

    private final Game game;

    public TextureListener(Game game) {
        this.game = game;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        game.start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        game.stop();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

}
