package ninja.hikaruna.lunandroid.feature;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.LinkedList;
import java.util.List;

import ninja.hikaruna.lunandroid.CollisionManager;
import ninja.hikaruna.lunandroid.Scene;
import ninja.hikaruna.lunandroid.Sprite;

/**
 * Created by hikaru on 2015/05/09.
 */
public class Collisionable extends Feature {

    public boolean debugMode;
    private CollisionManager manager;
    private Integer groupId;
    private List<OnCollisionListner> listeners;
    private boolean active;
    private boolean hit; // forDebug. if this frame already collisioned then true
    private Paint hitPaint; // forDebug

    public Collisionable() {
        listeners = new LinkedList<>();
        active = true;
        hitPaint = new Paint();
        hitPaint.setColor(Color.argb(128, 0, 255, 0));
        addOnCollisionListener(new OnCollisionListner() {
            @Override
            public void onCollision(Sprite other) {
                hit = true;
            }
        });
    }

    public void setGroup(Integer groupId) {
        Integer preId = this.groupId;
        this.groupId = groupId;
        if (manager != null) {
            removeSelf(preId);
            setCollisionReal();
        }
    }

    private void setCollisionReal() {
        manager.setCollicion(groupId, this);
    }


    @Override
    public void onSceneSetted(Scene scene) {
        manager = scene.getCollisionManager();
        setCollisionReal();
    }

    @Override
    public void onDraw(Canvas c) {
        if (debugMode) {
            if (hit) {
                c.drawRect(getRect(), hitPaint);
                hit = false;
            }
        }
    }

    @Override
    public void onDestroy() {
        removeSelf(groupId);
    }

    private void removeSelf(Integer groupId) {
        manager.getCollisionGroups().get(groupId).remove(this);
    }

    public RectF getRect() {
        return getSprite().getRect();
    }

    public void collision(Collisionable c2) {
        for (OnCollisionListner listner : listeners) {
            listner.onCollision(c2.getSprite());
        }

    }

    public void addOnCollisionListener(OnCollisionListner listener) {
        listeners.add(listener);
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public interface OnCollisionListner {
        void onCollision(Sprite other);
    }
}
