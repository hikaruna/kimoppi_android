package ninja.hikaruna.lunandroid;

import android.content.Context;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Scene extends SpriteGroup {
    private Game game;
    private ResourceManager resourceManager;
    private CollisionManager collisionManager;

    public void onCreate(Scene from, Game game) {
        this.game = game;
        this.resourceManager = new ResourceManager(getContext().getResources());
        this.collisionManager = new CollisionManager();
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

    @Override
    public synchronized void addChild(Sprite child) {
        super.addChild(child);
        child.onSceneSetted(this);
    }

    @Override
    public void update() {
        super.update();
        collisionManager.update();
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
}
