package com.lam.spritework;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lam.graphics.Argb;
import com.lam.graphics.sprites.ISpriteBackground;


public class SpriteBackgroundGradient implements ISpriteBackground {
    private Paint paint = new Paint();

    private int[] path;
    private int index = 0;
    private int direction = 1;

    public SpriteBackgroundGradient() {
        setColors(0xFFFF0000, 0xFF0000FF, 200);
    }

    public SpriteBackgroundGradient(int colorStart, int colorEnd, int steps) {
        setColors(colorStart, colorEnd, steps);
    }

    public void setColors(int colorStart, int colorEnd, int steps) {
        path = Argb.steps(colorStart, colorEnd, steps);
        paint.setAlpha(255);
        paint.setStrokeWidth(1);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        int banHeight = canvas.getHeight() / path.length;
        int y = 0;

        for (int aPath : path) {
            paint.setColor(aPath);
            canvas.drawRect(0, y, canvas.getWidth(), y + banHeight, paint);
            y += banHeight;
        }
    }
}
