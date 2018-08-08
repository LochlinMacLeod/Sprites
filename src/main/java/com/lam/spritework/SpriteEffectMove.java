package com.lam.spritework;

import com.lam.graphics.Argb;
import com.lam.graphics.Point3D;
import com.lam.graphics.sprites.SpriteEffect;

public class SpriteEffectMove extends SpriteEffect {
    private int deltaX;
    private int deltaY;
    private int deltaZ;;

    public SpriteEffectMove() {
    }

    public void set(int deltaX, int deltaY, int deltaZ) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }

    @Override
    public void onUpdate(int screenWidth, int screenHeight) {
        sprite.getPosition().add(deltaX, deltaY, deltaZ);
    }
}
