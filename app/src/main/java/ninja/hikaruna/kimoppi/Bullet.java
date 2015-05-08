package ninja.hikaruna.kimoppi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;

import ninja.hikaruna.lunandroid.Sprite;
import ninja.hikaruna.lunandroid.feature.Collisionable;
import ninja.hikaruna.lunandroid.feature.Physics;
import ninja.hikaruna.lunandroid.feature.Pictureble;

/**
 * Created by hikaru on 2015/05/09.
 */
public class Bullet extends Sprite {
    Picture picture;

    public Bullet(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.w = 100;
        this.h = 100;

        if (picture == null) {
            picture = new Picture();
            Canvas c = picture.beginRecording(w, h);
            Paint p = new Paint();
            p.setColor(Color.RED);
            c.drawOval(new RectF(0F, 0F, (float) w, (float) h), p);
            picture.endRecording();
        }

        useFeature(Pictureble.class).setPicture(picture);
        Physics physics = useFeature(Physics.class);
        physics.speedX = (int) (Math.random() * 10 - 5);
        physics.speedY = (int) (Math.random() * 10 - 5);

        Collisionable col = useFeature(Collisionable.class);
        col.setGroup(20);
        col.debugMode = true;
    }
}
