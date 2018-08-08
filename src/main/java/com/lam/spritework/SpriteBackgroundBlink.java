package com.lam.spritework;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lam.graphics.Argb;
import com.lam.graphics.sprites.ISpriteBackground;


public class SpriteBackgroundBlink implements ISpriteBackground {
    private Paint paint = new Paint();

    private int[] path;
    private int index = 0;
    private int direction = 1;
    private int pause = 0;
    private int speed = 25;

    public SpriteBackgroundBlink() {
        initialize(0xFFFF0000, 0xFF0000FF, 5);
    }

    public SpriteBackgroundBlink(int colorStart, int colorEnd, int steps) {
        initialize(colorStart, colorEnd, steps);
    }

    public void initialize(int colorStart, int colorEnd, int steps) {
        this.path = Argb.steps(colorStart, colorEnd, steps);

        paint.setAlpha(255);
        paint.setStrokeWidth(1);
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        pause++;

        if (speed <= pause) {
            index = index + direction;
            pause = 0;

            if (index < 0) {
                direction = 1;
                index = 0;
            }

            if (path.length <= index) {
                direction = -1;
                index = path.length - 1;
            }
        }

        paint.setColor(path[index]);

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }
}
