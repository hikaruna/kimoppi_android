package ninja.hikaruna.kimoppi.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import ninja.hikaruna.kimoppi.R;
import ninja.hikaruna.kimoppi.sprites.Bullet;
import ninja.hikaruna.kimoppi.sprites.Kimoppi;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.Scene;
import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Controllable;
import ninja.hikaruna.lunandroid.feature.Pictureble;

/**
 * Created by hikaru on 2015/05/08.
 */
public class MainScene extends Scene {
    @Override
    public void onCreate(Scene from, Game game) {
        super.onCreate(from, game);
        setBackground(Color.argb(100, 0, 32, 255));


        // リソースの登録
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
