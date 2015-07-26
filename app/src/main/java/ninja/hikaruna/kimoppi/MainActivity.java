package ninja.hikaruna.kimoppi;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ninja.hikaruna.kimoppi.scenes.MainScene;
import ninja.hikaruna.kimoppi.scenes.TouchScene;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.GameActivity;


public class MainActivity extends GameActivity {

    private MainScene mainScene;
    private TouchScene touchScene;

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
                if (getGame().getCurrentScene() instanceof MainScene) {
                    getGame().setCurrentScene(mainScene = new MainScene());
                } else {
                    getGame().setCurrentScene(mainScene);
                }
                return true;
            case R.id.action_scene_touch:
                if (getGame().getCurrentScene() instanceof TouchScene) {
                    getGame().setCurrentScene(touchScene = new TouchScene());
                } else {
                    getGame().setCurrentScene(touchScene);
                }
                return true;
        }
        return false;
    }

    @Override
    public void onGameCreated(Game game) {
        game.getFpsManager().setFps(30);
        mainScene = new MainScene();
        touchScene = new TouchScene();
        game.setCurrentScene(mainScene);
    }

    @Override
    public void onGameResume() {

    }

    @Override
    public void onGamePause() {

    }
}
