package ninja.hikaruna.lunandroid;

import android.content.Context;

/**
 * Created by hikaru on 2015/05/07.
 */
public class Scene extends SpriteGroup {
    private Game game;
    private ResourceManager resourceManager;

    public void onCreate(Scene from, Game game) {
        this.game = game;
        this.resourceManager = new ResourceManager(getContext().getResources());
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
        child.onSceneSetted();
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }
}
