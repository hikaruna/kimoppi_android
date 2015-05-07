package ninja.hikaru.kimoppi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;

import ninja.hikaru.kimoppi.lunandroid.Game;
import ninja.hikaru.kimoppi.lunandroid.Scene;


public class MainActivity extends ActionBarActivity implements TextureView.SurfaceTextureListener {

    private TextureView view;
    private Game game;
    private Kimoppi kimoppi;
    private boolean surfaceCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextureView) findViewById(R.id.view);
        view.setSurfaceTextureListener(this);
    }

    private void onSurfaceCreated() {
        game = new Game(view);
        game.getFpsManager().setFps(100);

        Scene scene = game.getCurrentScene();
        scene.setBackground(Color.argb(50, 255, 255, 255));

        Bitmap kimoppiResource = BitmapFactory.decodeResource(getResources(), R.drawable.kimoppi0);
        kimoppi = new Kimoppi(kimoppiResource);
        scene.addChild(kimoppi);
        for (int i = 0; i < 300; i++) {
            game.getCurrentScene().addChild(new Kimoppi(kimoppiResource));
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if(!surfaceCreated) {
            onSurfaceCreated();
        }

        kimoppi.x = 100;
        kimoppi.y = 100;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public TextureView getView() {
        return view;
    }
}
