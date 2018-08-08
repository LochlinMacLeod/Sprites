package com.lam.spritework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.lam.graphics.sprites.Sprite;

/**
 * SpriteImage derives from Sprite and holds an image to be
 * drawn for the sprite.
 */
public class SpriteImage extends Sprite {
    public Bitmap image;

    public SpriteImage(Bitmap image) {
        super(image.getWidth(), image.getHeight());
        this.image = image;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (image != null) {
            canvas.drawBitmap(image, matrix, null);
        }
    }
}
