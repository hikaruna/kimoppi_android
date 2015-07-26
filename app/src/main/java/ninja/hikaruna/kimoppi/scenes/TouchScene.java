package ninja.hikaruna.kimoppi.scenes;

import android.graphics.Color;

import ninja.hikaruna.kimoppi.sprites.Punipuni;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.Scene;

/**
 * Created by hikaru on 2015/07/17.
 */
public class TouchScene extends Scene {

    @Override
    public void onResume(Scene from, Game game) {
        super.onResume(from, game);
        setBackground(Color.argb(100, 0, 32, 255));

        Punipuni puni = new Punipuni();
        addChild(puni);
    }
}
