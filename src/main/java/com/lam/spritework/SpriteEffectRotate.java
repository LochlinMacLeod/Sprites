package com.lam.spritework;

import com.lam.graphics.Point3D;
import com.lam.graphics.sprites.SpriteEffect;

public class SpriteEffectRotate extends SpriteEffect {
    private int rotation = 0;
    private int spin = 0;

    public SpriteEffectRotate(int rotation, int spin) {
        set(rotation, spin);
    }

    public void set(int rotation, int spin) {
        this.rotation = rotation;
        this.spin = spin;
    }

    @Override
    protected void onUpdate(int screenWidth, int screenHeight) {
        if (sprite instanceof SpriteImage) {
            rotation += spin;
            while (rotation >= 360) {
                rotation -= 360;
            }

            while (rotation < 0) {
                rotation += 360;
            }

            Point3D point = sprite.getPosition();
            sprite.matrix.postRotate(rotation,
                    (float) (point.getX() + ((SpriteImage) sprite).image.getWidth() / 2.0),
                    (float) (point.getY() + ((SpriteImage) sprite).image.getHeight() / 2.0));
        }
        else {
            // Effect may only be assigned to a SpriteStar
            setComplete();
        }
    }
}

