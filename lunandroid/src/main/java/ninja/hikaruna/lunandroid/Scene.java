package ninja.hikaruna.lunandroid;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Scene extends SpriteGroup {
    private Game game;
    private ResourceManager resourceManager;
    private CollisionManager collisionManager;
    private ControllerManager controllerManager;

    public void onCreate(Scene from, Game game) {
        this.game = game;
        this.resourceManager = new ResourceManager(getContext().getResources());
        this.collisionManager = new CollisionManager();
        this.controllerManager = new ControllerManager();
        x = game.getWidth()/2;
        y = game.getHeight()/2;
        w = game.getWidth();
        h = game.getHeight();
    }

    @Override
    public Context getContext() {
        return game.getContext();
    }

    @Override
    public Scene getScene() {
        return this;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public synchronized void addChild(Sprite child) {
        super.addChild(child);
        child.onSceneSetted(this);
    }

    @Override
    public void update() {
        controllerManager.update();
        super.update();
        collisionManager.update();
    }

    @Override
    public SpriteGroup getParent() {
        return this;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public boolean onTouch(View v, MotionEvent event) {
        return controllerManager.onTouch(v, event);
    }
}
