/**
 * SpriteStar.java
 *
 * SpriteStar draws a point on the canvas as a sprite.  The width is used as
 * the size of the "star".  Height is ignored.
 */
package com.lam.spritework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lam.graphics.sprites.Sprite;

public class SpriteStar extends Sprite {
    private final static int defaultColor = Color.CYAN;
    private final static int defaultSize = 5;

    private static Paint paint = new Paint();

    private int color = defaultColor;


    public SpriteStar() {
        super(defaultSize, defaultSize);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(color);
        paint.setStrokeWidth(size.width);
        canvas.drawPoint(point.getX(), point.getY(), paint);
    }

    public void setSize(int size) {
        super.setSize(size, size, 0);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int clr) {
        this.color = clr;
    }
}
