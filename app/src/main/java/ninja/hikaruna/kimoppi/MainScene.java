package ninja.hikaruna.kimoppi;

import android.graphics.Color;

import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.Scene;

/**
 * Created by hikaru on 2015/05/08.
 */
public class MainScene extends Scene {
    @Override
    public void onCreate(Scene from, Game game) {
        super.onCreate(from, game);
        setBackground(Color.argb(100, 0, 32, 255));
        getResourceManager().setResource(R.drawable.kimoppi0);
        getResourceManager().setResource(R.drawable.kimoppi1);
        getResourceManager().setResource(R.drawable.kimoppi2);
        getResourceManager().setResource(R.drawable.kimoppi3);

        Kimoppi kimoppi = new Kimoppi();
        addChild(kimoppi);
        for (int i = 0; i < 30; i++) {
            addChild(new Bullet((int) (Math.random() * w), (int) (Math.random() * h)));
        }
    }
}
