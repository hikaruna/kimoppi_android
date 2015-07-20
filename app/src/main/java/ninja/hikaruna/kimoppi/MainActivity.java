package ninja.hikaruna.kimoppi;

import ninja.hikaruna.kimoppi.scenes.MainScene;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.GameActivity;


public class MainActivity extends GameActivity {

    @Override
    protected void onGameCreated(Game game) {
        game.getFpsManager().setFps(30);
        game.setCurrentScene(MainScene.class);
    }
}
