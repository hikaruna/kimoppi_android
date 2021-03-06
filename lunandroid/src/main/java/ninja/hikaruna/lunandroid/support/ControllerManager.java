package ninja.hikaruna.lunandroid.support;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ninja.hikaruna.lunandroid.ControllEvent;
import ninja.hikaruna.lunandroid.feature.Controllable;

/**
 * Created by hikaru on 2015/05/10.
 */
public class ControllerManager {

    private Set<Controllable> targets;

    /**
     * 1フレーム中の入力は配列にして格納
     * updateが呼ばれるたびにControlableへ送信される.
     */
    private List<ControllEvent> events;

    public ControllerManager() {
        targets = new HashSet<>();
        events = new ArrayList<>();
    }

    public void add(Controllable controllable) {
        targets.add(controllable);
    }

    public boolean onControll(ControllEvent event) {
        events.add(event);
        return true;
    }

    public void update() {
        if(!events.isEmpty()) {
            for (Controllable target : targets) {
                ControllEvent event = events.get(events.size() - 1);
                target.onControll(event);
            }
        }
        events = new ArrayList<>();
    }
}
