package ninja.hikaru.kimoppi.lunandroid;

import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hikaru on 2015/05/07.
 */
public class SpriteGroup extends Sprite {
    List<Sprite> children;

    public SpriteGroup() {
        super();
        children = new LinkedList<>();
    }

    public void addChild(Sprite child) {
        children.add(child);
    }

    public void removeChild(Sprite child) {
        children.remove(child);
    }


    public void update() {
        super.update();
        for(Sprite child : children) {
            child.update();
        }
    }

    @Override
    public final void onDraw(Canvas c) {
        for(Sprite child : children) {
            child.draw();
            c.drawPicture(child.getPicture());
        }
    }

    @Override
    public void draw2(Canvas c) {
        super.draw2(c);
        for(Sprite child : children) {
            child.draw2(c);
        }
    }
}
