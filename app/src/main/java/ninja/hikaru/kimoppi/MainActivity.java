package ninja.hikaru.kimoppi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;

import ninja.hikaru.kimoppi.lunandroid.Game;
import ninja.hikaru.kimoppi.lunandroid.Scene;


public class MainActivity extends ActionBarActivity {

    private TextureView view;
    private Game game;
    private Kimoppi kimoppi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextureView) findViewById(R.id.view);
        game = new Game(view);
        view.setSurfaceTextureListener(new TextureListener(game));
        game.getFpsManager().setFps(100);

        Bitmap kimoppiResource = BitmapFactory.decodeResource(getResources(), R.drawable.kimoppi0);
        kimoppi = new Kimoppi(kimoppiResource);
        game.getCurrentScene().addChild(kimoppi);
        for(int i = 0 ; i< 300; i++) {
            game.getCurrentScene().addChild(new Kimoppi(kimoppiResource));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        kimoppi.x = 100;
        kimoppi.y = 100;
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
