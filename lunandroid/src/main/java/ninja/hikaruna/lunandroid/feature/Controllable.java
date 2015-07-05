package ninja.hikaruna.lunandroid.feature;

import android.view.MotionEvent;

import ninja.hikaruna.lunandroid.ControllerManager;
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

    public void onControll(MotionEvent event) {
        if(listener == null) {
            return;
        }
        listener.onControll(event);
    }

    public interface Controller {
        void onControll(MotionEvent event);
    }
}
