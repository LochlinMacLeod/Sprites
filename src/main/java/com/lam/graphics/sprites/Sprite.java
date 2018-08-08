/**
 * Sprite.java
 *
 * Sprite is the base class for a sprite.  It is abstract an must be derived from.
 */
package com.lam.graphics.sprites;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.lam.graphics.Point3D;
import com.lam.graphics.Size3D;

public abstract class Sprite {
    // Current state of the sprite
    SpriteState state;

    // Location
    protected Point3D point = new Point3D();
    protected Size3D size = new Size3D();

    // Drawing
    protected boolean visible = false;
    public Matrix matrix = new Matrix();

    /**
     * Sprite constructor.  Initialize the object.
     */
    public Sprite(int height, int width) {
        this.state = SpriteState.Inactive;
        this.visible = false;
    }

    final public Point3D getPosition() {
        return point;
    }

    /**
     * New position of the sprite on the screen.
     * @param x Horizontal position
     * @param y Vertical position
     * @param z Deep position
     */
    final public void setPosition(float x, float y, float z) {
        point.setX(x);
        point.setY(y);
        point.setZ(z);
    }

    /**
     * New position of the sprite on the screen.
     * @param point a position on the screen
     */
    final public void setPosition(Point3D point) {
        this.point.setX(point.getX());
        this.point.setY(point.getY());
        this.point.setZ(point.getZ());
    }

    /**
     */
    final public Size3D getSize() {
        return size;
    }

    /**
     * setSize resets the size of the sprite.
     * @param width New horizontal size
     * @param height New vertical size
     */
    final public void setSize(float width, float height, float depth) {
        size.width = width;
        size.height = height;
        size.depth = depth;
    }

    /**
     * setSize resets the size of the sprite.
     * @param size
     */
    final public void setSize(Size3D size) {
        this.size.width = size.width;
        this.size.height = size.height;
        this.size.depth = size.depth;
    }

    /**
     * setVisible set if the Sprite is visible
     * @param state Sets the visibility state of the Sprite
     */
    final public void setVisible(boolean state) {
        visible = state;
    }

    final boolean getActive() {
        return state == SpriteState.Active;
    }

    /**
     * setActive sets the sprite state to active
     */
    void setActive() {
        state = SpriteState.Active;
    }

    /**
     * release set the sprite up for release
     */
    public void setComplete() {
        state = SpriteState.Complete;
    }

    /**
     * release set the sprite up for release
     */
    public void setInactive() {
        state = SpriteState.Inactive;
    }

    /**
     * update performs the effects on the Sprite
     */
    final protected void reset() {
        matrix.reset();
        matrix.postTranslate(point.getX(), point.getY());
    }

    /**
     * draw puts the Sprite to the given Canvas.  This confirms the
     * Sprite is visible and active.
     * @param canvas Where to draw this Sprite
     */
    final protected void draw(Canvas canvas) {
        if (visible && getActive()) {
            onDraw(canvas);
        }
    }

    /**
     * onDraw performs the draw of the Sprite to the given Canvas.
     * @param canvas Where to draw this Sprite
     */
    public abstract void onDraw(Canvas canvas);
}
