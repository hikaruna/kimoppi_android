package ninja.hikaruna.kimoppi.scenes;

import android.graphics.Color;
import android.view.MotionEvent;

import ninja.hikaruna.kimoppi.R;
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
        getResourceManager().setResource(R.drawable.kimoppi0);
        getResourceManager().setResource(R.drawable.kimoppi1);
        getResourceManager().setResource(R.drawable.kimoppi2);
        getResourceManager().setResource(R.drawable.kimoppi3);

        /*
        Kimoppi kimoppi = new Kimoppi();
        addChild(kimoppi);
        for (int i = 0; i < 30; i++) {
            addChild(new Bullet((int) (Math.random() * w), (int) (Math.random() * h)));
        }
        */

        final Sprite sprite = new Sprite();
        sprite.w = 30;
        sprite.h = 30;
        sprite.setBackground(Color.WHITE);
        sprite.useFeature(Controllable.class).setController(new Controllable.Controller() {
            @Override
            public void onControll(MotionEvent event) {
                sprite.x = (int) event.getX();
                sprite.y = (int) event.getY();
            }
        });
        addChild(sprite);
    }
}
