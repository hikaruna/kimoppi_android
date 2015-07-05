package ninja.hikaruna.lunandroid.util;

import java.util.Vector;

/**
 * Created by hikaru on 2015/07/05.
 */
public class Vector2D {
    private float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getScale() {
        return (float) Math.sqrt((double) ((x * x) + (y + y)));
    }

    public Vector2D getNomalize() {
        return new Vector2D(x/getScale(), y/getScale());
    }

    public Vector2D addition(Vector2D other) {
        return new Vector2D(x + other.getX(), y + other.getY());
    }

    public Vector2D subtraction(Vector2D other) {
        return new Vector2D(x - other.getX(), y - other.getY());
    }

    public Vector2D multiplication(float other) {
        return new Vector2D(x * other, y * other);
    }
}
