package ninja.hikaru.kimoppi;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;

import ninja.hikaru.kimoppi.lunandroid.RootView;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends RootView {

    public int x;
    public int y;

    @Override
    public int Resource() {
        return R.drawable.kimoppi0;
    }

    @Override
    public void update() {
        x+= Math.random() * 4 - 2;
        y+= Math.random() * 4 - 2;
    }
}
