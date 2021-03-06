package ninja.hikaruna.lunandroid.feature;

import android.view.MotionEvent;

import ninja.hikaruna.lunandroid.ControllEvent;
import ninja.hikaruna.lunandroid.support.ControllerManager;
import ninja.hikaruna.lunandroid.Scene;

/**
 * Created by hikaru on 2015/05/18.
 */
public class Controllable extends Feature {

    private ControllerManager manager;
    private Controller listener;

    @Override
    public void onSceneSetted(Scene scene) {
        manager = scene.getControllerManager();
        manager.add(this);
    }

    public void setController(Controller controller) {
        listener = controller;
    }

    public void onControll(ControllEvent event) {
        if(listener == null) {
            return;
        }
        event.setCurrent(getSprite().getParent());
        listener.onControll(event);
    }

    public interface Controller {
        void onControll(ControllEvent event);
    }
}
