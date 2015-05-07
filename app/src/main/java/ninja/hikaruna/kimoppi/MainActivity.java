package ninja.hikaruna.kimoppi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import ninja.hikaru.kimoppi.R;
import ninja.hikaruna.lunandroid.Game;
import ninja.hikaruna.lunandroid.GameActivity;
import ninja.hikaruna.lunandroid.Scene;


public class MainActivity extends GameActivity {

    @Override
    protected void onGameCreated(Game game) {
        game.getFpsManager().setFps(100);

        Scene scene = game.getCurrentScene();
        scene.setBackground(Color.argb(50, 255, 255, 255));

        Bitmap kimoppiResource = BitmapFactory.decodeResource(getResources(), R.drawable.kimoppi0);
        Kimoppi kimoppi = new Kimoppi(kimoppiResource);
        scene.addChild(kimoppi);
        for (int i = 0; i < 300; i++) {
            scene.addChild(new Kimoppi(kimoppiResource));
        }
    }
}
