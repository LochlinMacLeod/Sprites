package com.lam.spritework;

import com.lam.graphics.Point3D;
import com.lam.graphics.Size3D;
import com.lam.graphics.sprites.SpriteEffect;

public class SpriteEffectBoundaryWrap extends SpriteEffect {

    public SpriteEffectBoundaryWrap() {
    }

    @Override
    public void onUpdate(int screenWidth, int screenHeight) {
        Point3D position = sprite.getPosition();
        Size3D size = sprite.getSize();

        // Keep on the screen
        if (position.getX() + size.width < 0) {
            position.setX(screenWidth);
        }
        if (screenWidth < position.getX()) {
            position.setX(0 - size.width);
        }
        if (position.getY() + size.height < 0) {
            position.setY(screenHeight);
        }
        if (size.height < position.getY()) {
            position.setY(0 - size.height);
        }
        if (position.getZ() < 0) {
            position.setZ(0);
        }
        if (10000 < position.getZ()) {
            position.setZ(10000);
        }
        sprite.setPosition(position);
    }
}
