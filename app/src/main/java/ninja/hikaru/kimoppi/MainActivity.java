package ninja.hikaru.kimoppi;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextureView) findViewById(R.id.view);
        game = new Game(view);
        view.setSurfaceTextureListener(new TextureListener(game));

        Kimoppi kimoppi = new Kimoppi(this.getResources());
        game.getCurrentScene().addChild(kimoppi);
        for(int i = 0 ; i< 300; i++) {
            //game.getCurrentScene().addChild(new Kimoppi(getResources()));
        }
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
