package ninja.hikaru.kimoppi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ninja.hikaru.kimoppi.lunandroid.Animation;
import ninja.hikaru.kimoppi.lunandroid.Physics;
import ninja.hikaru.kimoppi.lunandroid.Sprite;

/**
 * Created by hikaru on 2015/05/06.
 */
public class Kimoppi extends Sprite {

    private static Bitmap res;
    private static Bitmap res1;
    private static Bitmap res2;
    private static Bitmap res3;
    private Bitmap resource;

    public Kimoppi(Resources resources) {
        if (Kimoppi.res == null) {
            Kimoppi.res = BitmapFactory.decodeResource(resources, R.drawable.kimoppi0);
        }
        if (Kimoppi.res1 == null) {
            Kimoppi.res1 = BitmapFactory.decodeResource(resources, R.drawable.kimoppi1);
        }
        if (Kimoppi.res2 == null) {
            Kimoppi.res2 = BitmapFactory.decodeResource(resources, R.drawable.kimoppi2);
        }
        if (Kimoppi.res3 == null) {
            Kimoppi.res3 = BitmapFactory.decodeResource(resources, R.drawable.kimoppi3);
        }
        x = (int) (Math.random() * 1000);
        y = (int) (Math.random() * 2000);
        w = 100;
        h = 100;
        useFeature(Physics.class);

        Animation animation = useFeature(Animation.class);
        Bitmap[] images = {
                res, res1, res2, res3
        };
        animation.setImages(images);
        int[] indexs = {0, 1, 2, 3, 2, 1};
        animation.setIndexs(indexs);
        animation.setWait(20);
        animation.setLoop(false);
    }

    @Override
    public void update() {
        super.update();

        useFeature(Physics.class).speedX += (Math.random() * 3) - 1.5f;
        useFeature(Physics.class).speedY += (Math.random() * 3) - 1.5f;
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }
}
