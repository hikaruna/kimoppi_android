package ninja.hikaru.kimoppi.lunandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Map;

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
