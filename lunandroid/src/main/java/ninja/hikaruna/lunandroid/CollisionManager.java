package ninja.hikaruna.lunandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ninja.hikaruna.lunandroid.feature.Collisionable;

/**
 * Created by hikaru on 2015/05/09.
 */
public class CollisionManager {
    private Map<Integer, List<Collisionable>> collisionGroups;
    private Integer group;

    public CollisionManager() {
        collisionGroups = new HashMap<>(5);
        useGroup(null);
    }

    public void setCollicion(Integer groupId, Collisionable collisionable) {
        useGroup(groupId).add(collisionable);
    }

    private List<Collisionable> useGroup(Integer groupId) {
        if (!collisionGroups.containsKey(groupId)) {
            setGroup(groupId);
        }
        return getGroup(groupId);
    }

    private List<Collisionable> getGroup(Integer groupId) {
        return collisionGroups.get(groupId);
    }

    public void setGroup(Integer groupId) {
        collisionGroups.put(groupId, new ArrayList<Collisionable>());
    }

    public Map<Integer, List<Collisionable>> getCollisionGroups() {
        return collisionGroups;
    }

    public void update() {
        for (Map.Entry<Integer, List<Collisionable>> g1 : collisionGroups.entrySet()) {
            if (g1.getValue().isEmpty()) {
                continue;
            }
            for (Map.Entry<Integer, List<Collisionable>> g2 : collisionGroups.entrySet()) {
                if (g2.getValue().isEmpty()) {
                    continue;
                }
                if (g1.getKey() != null && g1.getKey().equals(g2.getKey())) {
                    continue;
                }
                for (Collisionable c1 : g1.getValue()) {
                    if (!c1.getActive()) {
                        continue;
                    }
                    for (Collisionable c2 : g2.getValue()) {
                        if (!c2.getActive()) {
                            continue;
                        }
                        if (c1.equals(c2)) {
                            continue;
                        }
                        if (c1.getRect().intersect(c2.getRect())) {
                            c1.collision(c2);
                        }
                    }
                }
            }
        }
    }
}
