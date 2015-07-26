package ninja.hikaruna.lunandroid;

import android.database.sqlite.SQLiteAccessPermException;
import android.view.MotionEvent;

/**
 * Created by hikaru on 2015/07/26.
 */
public class ControllEvent {

    public int gameOffsetX;
    public int gameOffsetY;
    public SpriteGroup current;

    private final MotionEvent event;
    private final Game game;

    public ControllEvent(MotionEvent event, Game game) {
        this.event = event;
        this.game = game;

    }

    public void setCurrent(SpriteGroup current) {
        this.current = current;
    }

    public int getX() {
        return (int) (event.getX() - game.getLocationInWindowX() - current.getLeft());
    }

    public int getY() {
        return (int) (event.getY() - game.getLocationInWindowY() - current.getTop());
    }
}
