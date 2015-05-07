package com.example.lunandroid.lunandroid;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.TextureView;

import com.example.lunandroid.R;

/**
 * Created by hikaru on 2015/05/08.
 */
public class GameActivity extends Activity implements TextureView.SurfaceTextureListener {

    private boolean gameCreated;
    private TextureView view;
    private Game game;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        view = (TextureView) findViewById(R.id.gameview);
        if (view == null) {
            setContentView(R.layout.acrivity_game);
            view = (TextureView) findViewById(R.id.gameview);
        }
        view.setSurfaceTextureListener(this);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (!gameCreated) {
            this.game = new Game(view);
            onGameCreated(game);
        }
        onGameResume();
    }

    protected void onGameCreated(Game game) {
    }

    protected void onGameResume() {
        game.start();
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    public void onGamePause() {
        game.stop();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        onGamePause();
        return false;
    }

    private void onGameDestroy() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onGameDestroy();
    }

}
