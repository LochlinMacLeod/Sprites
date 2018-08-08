package com.lam.graphics.sprites;

import android.graphics.Canvas;

/**
 * SpriteBackground is an interface for building
 * an object to display as background on a Canvas.
 */
public interface ISpriteBackground {
    /**
     * update allows for the update of the background in a frame
     */
    void update();

    /**
     * draw puts the background to the given canvas
     * @param canvas
     */
    void draw(Canvas canvas);
}
