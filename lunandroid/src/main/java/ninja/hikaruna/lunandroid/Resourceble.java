package ninja.hikaruna.lunandroid;

import android.support.annotation.Nullable;

/**
 * Created by hikaru on 2015/05/08.
 */
public class Resourceble extends Feature {

    private Integer resId;

    public void setResource(int resId) {
        this.resId = resId;
        if (getSprite().getScene() != null) {
            setResourceReal();
        }
    }

    private void setResourceReal() {
        getPictureble().setPicture(getSprite().getScene().getResourceManager().getPicture(resId));
    }

    @Override
    public void onSceneSetted() {
        if (resId != null) {
            setResourceReal();
        }
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public Class<? extends Feature>[] getDepends() {
        return new Class[]{Pictureble.class};
    }

    private Pictureble getPictureble() {
        return getSprite().getFeature(Pictureble.class);
    }
}

