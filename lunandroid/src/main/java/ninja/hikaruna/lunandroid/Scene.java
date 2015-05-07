package ninja.hikaruna.lunandroid;

import android.content.Context;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Scene extends SpriteGroup {
    private Game game;

    public void onCreate(Scene from, Game game) {
        this.game = game;
        x = game.getWidth()/2;
        y = game.getHeight()/2;
        w = game.getWidth();
        h = game.getHeight();
    }

    @Override
    public Context getContext() {
        return game.getContext();
    }

    @Override
    public Scene getScene() {
        return this;
    }

}
