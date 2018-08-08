package com.lam.spritework;

import com.lam.graphics.Argb;
import com.lam.graphics.sprites.SpriteEffect;

public class SpriteEffectPulse extends SpriteEffect {
    private static int minValue = 50;
    private static int maxValue = 250;

    int direction = -1;

    public SpriteEffectPulse() {
        super();
    }

    @Override
    protected void onUpdate(int screenWidth, int screenHeight) {
        if (sprite instanceof SpriteStar) {
            SpriteStar star = (SpriteStar) sprite;
            int color = star.getColor();

            int alpha = Argb.getAlpha(color) + direction;

            if (direction  < minValue ) {
                direction = 1;
            }

            if (maxValue < direction ) {
                direction = -1;
            }
        }
        else {
            // Effect may only be assigned to a SpriteStar
            setComplete();
        }
    }
}
