package ninja.hikaruna.kimoppi;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ninja.hikaruna.kimoppi.scenes.MainScene;
import ninja.hikaruna.kimoppi.scenes.TouchScene;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.GameActivity;


public class MainActivity extends GameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_scene_main:
                getGame().setCurrentScene(MainScene.class);
                return true;
            case R.id.action_scene_touch:
                getGame().setCurrentScene(TouchScene.class);
                return true;
        }
        return false;
    }

    @Override
    protected void onGameCreated(Game game) {
        game.getFpsManager().setFps(30);
        game.setCurrentScene(MainScene.class);
    }
}
