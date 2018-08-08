package com.lam.spritework;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lam.graphics.sprites.ISpriteBackground;

public class SpriteBackgroundSolid implements ISpriteBackground {
    private Paint paint = new Paint();

    private int color;

    public SpriteBackgroundSolid() {
        setColor(0xFFFFFFFF);
    }

    public SpriteBackgroundSolid(int color) {
        setColor(color);
    }

    public void setColor(int color) {
        this.color = color;

        paint.setColor(this.color);
        paint.setAlpha(255);
        paint.setStrokeWidth(1);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }
}
