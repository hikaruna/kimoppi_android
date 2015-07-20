package ninja.hikaruna.kimoppi.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import ninja.hikaruna.kimoppi.R;
import ninja.hikaruna.kimoppi.sprites.Punipuni;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.Scene;
import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Controllable;

/**
 * Created by hikaru on 2015/07/17.
 */
public class TouchScene extends Scene {

    @Override
    public void onCreate(Scene from, Game game) {
        super.onCreate(from, game);
        setBackground(Color.argb(100, 0, 32, 255));

        Punipuni puni = new Punipuni();
        addChild(puni);
    }
}
