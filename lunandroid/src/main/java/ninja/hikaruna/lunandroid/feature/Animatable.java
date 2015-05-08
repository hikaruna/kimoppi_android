package ninja.hikaruna.lunandroid.feature;

import android.graphics.Picture;
import android.support.annotation.Nullable;

/**
 * Created by hikaru on 2015/05/08.
 */
public class Animatable extends Feature {

    /**
     * Integer
     */
    private Object[] animation;
    private int deley;
    private long tick;

    public Animatable() {
        deley = 6;
    }


    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Feature>[] getDepends() {
        return new Class[]{Pictureble.class};
    }

    /**
     * This method depends Resourceble.
     *
     * @param animation item.
     *                  Enable Type is only resId:Integer.
     *                  resId:Integer is depends Resourceble.
     */
    public void setAnimation(Object[] animation) {
        for (Object item : animation) {
            if (!(item instanceof Integer) && !(item instanceof Picture)) {
                throw new IllegalArgumentException();
            }

            if (item instanceof Integer) {
                if (!getSprite().containsFeature(Resourceble.class)) {
                    throw new RuntimeException("setAnimation(Object<Integer>[]) dpends Resourceble.");
                }
            }
        }

        this.animation = animation;
    }

    @Override
    public void onUpdate() {
        if (animation == null) {
            return;
        }

        Object item = animation[((int) ((tick++ / deley) % animation.length))];
        if (item instanceof Integer) {
            int resId = (int) item;
            getSprite().getFeature(Resourceble.class).setResource(resId);
        } else {
            Picture picture = (Picture) item;
            getSprite().getFeature(Pictureble.class).setPicture(picture);
        }
    }
}
