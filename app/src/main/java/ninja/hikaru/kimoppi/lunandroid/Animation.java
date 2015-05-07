package ninja.hikaru.kimoppi.lunandroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Animation extends Feature {

    int wait;
    Bitmap[] images;
    int[] indexs;
    boolean loop;

    private int count;

    public Animation() {
        wait = 1;
    }

    @Override
    public void onDraw(Canvas c) {
        if(loop) {
            c.drawBitmap(images[indexs[(count++ / wait) % indexs.length]], null, getSprite().getRect(), null);
        }else {
            int i = count++ / wait;
            if(i < indexs.length) {
                c.drawBitmap(images[indexs[i]],null, getSprite().getRect(), null);
            }else {
                c.drawBitmap(images[indexs[indexs.length-1]],null, getSprite().getRect(), null);
            }
        }
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;

        if(indexs == null) {
            indexs = new int[images.length];
            for(int i=0;i<images.length;i++) {
                indexs[i] = i;
            }
        }
    }

    public void setIndexs(int[] indexs) {
        this.indexs = indexs;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
